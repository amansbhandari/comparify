package ca.dal.comparify.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AlertRepository.class})
@ExtendWith(SpringExtension.class)
class AlertRepositoryTest {
    @Autowired
    private AlertRepository alertRepository;

    @MockBean
    private MongoRepository mongoRepository;

    @Test
    void testSave() {
        when(this.mongoRepository.insertOne((String) any(), (AlertModel) any(), (Class<AlertModel>) any())).thenReturn(1);
        assertEquals(1, this.alertRepository.save(new AlertModel()));
        verify(this.mongoRepository).insertOne((String) any(), (AlertModel) any(), (Class<AlertModel>) any());
    }

    @Test
    void testGetAlerts() {
        ArrayList<Object> objectList = new ArrayList<>();
        when(this.mongoRepository.aggregate((String) any(), (List<org.bson.conversions.Bson>) any(), (Class<Object>) any()))
            .thenReturn(objectList);
        List<AlertResponseModel> actualAlerts = this.alertRepository.getAlerts("42");
        assertSame(objectList, actualAlerts);
        assertTrue(actualAlerts.isEmpty());
        verify(this.mongoRepository).aggregate((String) any(), (List<org.bson.conversions.Bson>) any(),
            (Class<Object>) any());
    }

    @Test
    void testDelete() {
        when(this.mongoRepository.deleteOne((String) any(), (org.bson.conversions.Bson) any())).thenReturn(true);
        assertTrue(this.alertRepository.delete("42", "42"));
        verify(this.mongoRepository).deleteOne((String) any(), (org.bson.conversions.Bson) any());
    }

    @Test
    void testDelete2() {
        when(this.mongoRepository.deleteOne((String) any(), (org.bson.conversions.Bson) any())).thenReturn(false);
        assertFalse(this.alertRepository.delete("42", "42"));
        verify(this.mongoRepository).deleteOne((String) any(), (org.bson.conversions.Bson) any());
    }

    @Test
    void testCheckForAlerts() {
        HashModel hashModel = new HashModel();
        when(this.mongoRepository.aggregateOne((String) any(), (java.util.List<org.bson.conversions.Bson>) any(),
            (Class<Object>) any())).thenReturn(hashModel);
        HashModel actualCheckForAlertsResult = this.alertRepository.checkForAlerts("42", "42");
        assertSame(hashModel, actualCheckForAlertsResult);
        assertTrue(actualCheckForAlertsResult.isEmpty());
        verify(this.mongoRepository).aggregateOne((String) any(), (java.util.List<org.bson.conversions.Bson>) any(),
            (Class<Object>) any());
    }

    @Test
    void testFetchAlertById() {
        AlertModel alertModel = new AlertModel();
        when(this.mongoRepository.findOne((String) any(), (org.bson.conversions.Bson) any(), (Class<Object>) any()))
            .thenReturn(alertModel);
        assertSame(alertModel, this.alertRepository.fetchAlertById("42"));
        verify(this.mongoRepository).findOne((String) any(), (org.bson.conversions.Bson) any(), (Class<Object>) any());
    }
}

