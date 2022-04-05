package ca.dal.comparify.alerts;

import static ca.dal.comparify.alerts.model.AlertTypeEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.brand.BrandService;
import ca.dal.comparify.item.ItemService;
import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.model.RangeModel;
import ca.dal.comparify.notification.NotificationService;
import ca.dal.comparify.store.StoreService;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.service.UserDetailsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AlertService.class})
@ExtendWith(SpringExtension.class)
class AlertServiceTest {

    @MockBean
    private AlertRepository alertRepository;

    @Autowired
    private AlertService alertService;

    @MockBean
    private BrandService brandService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private StoreService storeService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void testCreate() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.save((ca.dal.comparify.alerts.model.AlertModel) any())).thenReturn(1);
        assertEquals(1, this.alertService.create(new AlertRequestModel(), "Jan 1, 2020 8:00am GMT+0100"));
        verify(this.alertRepository).save((ca.dal.comparify.alerts.model.AlertModel) any());
    }

    @Test
    void testCreate2() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.save((ca.dal.comparify.alerts.model.AlertModel) any())).thenReturn(0);
        assertEquals(0, this.alertService.create(new AlertRequestModel(), "Jan 1, 2020 8:00am GMT+0100"));
        verify(this.userDetailsService).findUserById((String) any());
        verify(this.notificationService).send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any());
        verify(this.alertRepository).save((ca.dal.comparify.alerts.model.AlertModel) any());
    }

    @Test
    void testCreate3() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.save((AlertModel) any())).thenReturn(1);
        assertEquals(1, this.alertService.create(new AlertModel(), "Jan 1, 2020 8:00am GMT+0100"));
        verify(this.alertRepository).save((AlertModel) any());
    }

    @Test
    void testCreate6() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.save((AlertModel) any())).thenReturn(0);
        assertEquals(0, this.alertService.create(new AlertModel(), "Jan 1, 2020 8:00am GMT+0100"));
        verify(this.userDetailsService).findUserById((String) any());
        verify(this.notificationService).send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any());
        verify(this.alertRepository).save((AlertModel) any());
    }

    @Test
    void testFetch() {
        ArrayList<AlertResponseModel> alertResponseModelList = new ArrayList<>();
        when(this.alertRepository.getAlerts((String) any())).thenReturn(alertResponseModelList);
        List<AlertResponseModel> actualFetchResult = this.alertService.fetch("42");
        assertSame(alertResponseModelList, actualFetchResult);
        assertTrue(actualFetchResult.isEmpty());
        verify(this.alertRepository).getAlerts((String) any());
    }

    @Test
    void testDelete() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.delete((String) any(), (String) any())).thenReturn(true);
        when(this.alertRepository.fetchAlertById((String) any())).thenReturn(new AlertModel());
        assertTrue(this.alertService.delete("42", "42"));
        verify(this.userDetailsService).findUserById((String) any());
        verify(this.notificationService).send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any());
        verify(this.alertRepository).delete((String) any(), (String) any());
        verify(this.alertRepository).fetchAlertById((String) any());
    }

    @Test
    void testDelete3() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.delete((String) any(), (String) any())).thenReturn(false);
        when(this.alertRepository.fetchAlertById((String) any())).thenReturn(new AlertModel());
        assertFalse(this.alertService.delete("42", "42"));
        verify(this.alertRepository).delete((String) any(), (String) any());
        verify(this.alertRepository).fetchAlertById((String) any());
    }

    @Test
    void testDelete4() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        when(this.alertRepository.delete((String) any(), (String) any())).thenReturn(true);
        when(this.alertRepository.fetchAlertById((String) any())).thenReturn(null);
        assertFalse(this.alertService.delete("42", "42"));
        verify(this.alertRepository).fetchAlertById((String) any());
    }

    @Test
    void testDelete5() {
        when(this.userDetailsService.findUserById((String) any())).thenReturn(new UserDetailsModel());
        when(this.notificationService.send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any())).thenReturn(true);
        AlertModel alertModel = mock(AlertModel.class);
        when(alertModel.getAlertIdentifier()).thenReturn("42");
        when(this.alertRepository.delete((String) any(), (String) any())).thenReturn(true);
        when(this.alertRepository.fetchAlertById((String) any())).thenReturn(alertModel);
        assertTrue(this.alertService.delete("42", "42"));
        verify(this.userDetailsService).findUserById((String) any());
        verify(this.notificationService).send((String) any(),
            (ca.dal.comparify.framework.notification.model.MailNotificationModel) any());
        verify(this.alertRepository).delete((String) any(), (String) any());
        verify(this.alertRepository).fetchAlertById((String) any());
        verify(alertModel, atLeast(1)).getAlertIdentifier();
    }

    @Test
    @Disabled
    void testTrigger() {

        AlertModel actualAlertModel = new AlertModel();
        actualAlertModel.setAlertIdentifier("42");
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualAlertModel.setAudit(createResult);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualAlertModel.setExpiresOn(fromResult);
        actualAlertModel.setId("42");
        RangeModel<Integer> rangeModel = new RangeModel<>();
        actualAlertModel.setPriceRange(rangeModel);
        actualAlertModel.setStatus(true);

        HashModel activeAlerts = new HashModel();
        activeAlerts.put(PRODUCT_INFORMATION_AVAILABLE.getValueLowerCase(),
            Collections.singleton(actualAlertModel));

        activeAlerts.put(PRICE_RANGE.getValueLowerCase(),
            Collections.singleton(actualAlertModel));

        activeAlerts.put(PRICE_DROP.getValueLowerCase(),
            Collections.singleton(actualAlertModel));

        when(this.alertRepository.checkForAlerts(any(), any())).thenReturn(activeAlerts);

        this.alertService.trigger("sampleBrandId", "sampleProductId",
            10.0d, "sampleStoreId");
    }
}

