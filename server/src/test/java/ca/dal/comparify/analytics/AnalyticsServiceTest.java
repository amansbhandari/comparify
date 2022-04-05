package ca.dal.comparify.analytics;

import ca.dal.comparify.itemcategories.ItemCategoryService;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import ca.dal.comparify.model.HashModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AnalyticsService.class})
@ExtendWith(SpringExtension.class)
class AnalyticsServiceTest {
    @MockBean
    private AnalyticsRepository analyticsRepository;

    @Autowired
    private AnalyticsService analyticsService;

    @MockBean
    private ItemCategoryService itemCategoryService;

    @Test
    void testGetPriceTrend() {
        ArrayList<HashModel> hashModelList = new ArrayList<>();
        when(this.analyticsRepository.getPriceTrend((String) any())).thenReturn(hashModelList);
        List<HashModel> actualPriceTrend = this.analyticsService.getPriceTrend("42");
        assertSame(hashModelList, actualPriceTrend);
        assertTrue(actualPriceTrend.isEmpty());
        verify(this.analyticsRepository).getPriceTrend((String) any());
    }

    @Test
    void testGetProductCountForCategory() {
        ArrayList<HashModel> hashModelList = new ArrayList<>();
        when(this.analyticsRepository.getProductCountForCategory((java.time.LocalDate) any())).thenReturn(hashModelList);
        List<HashModel> actualProductCountForCategory = this.analyticsService.getProductCountForCategory("2020-03-01");
        assertSame(hashModelList, actualProductCountForCategory);
        assertTrue(actualProductCountForCategory.isEmpty());
        verify(this.analyticsRepository).getProductCountForCategory((java.time.LocalDate) any());
    }

    @Test
    void testGetMonthlyTotalPurchaseOfItemCategory() {
        when(this.analyticsRepository.getAllCategories()).thenReturn(new ArrayList<>());
        when(this.analyticsRepository.getMonthlyTotalPurchaseOfItemCategory(anyInt())).thenReturn(new ArrayList<>());
        assertTrue(this.analyticsService.getMonthlyTotalPurchaseOfItemCategory(1).isEmpty());
        verify(this.analyticsRepository).getAllCategories();
        verify(this.analyticsRepository).getMonthlyTotalPurchaseOfItemCategory(anyInt());
    }

    @Test
    void testGetMonthlyTotalPurchaseOfItemCategory3() {
        ArrayList<HashModel> hashModelList = new ArrayList<>();
        hashModelList.add(new HashModel());
        when(this.analyticsRepository.getAllCategories()).thenReturn(new ArrayList<>());
        when(this.analyticsRepository.getMonthlyTotalPurchaseOfItemCategory(anyInt())).thenReturn(hashModelList);
        assertTrue(this.analyticsService.getMonthlyTotalPurchaseOfItemCategory(1).isEmpty());
        verify(this.analyticsRepository).getAllCategories();
        verify(this.analyticsRepository).getMonthlyTotalPurchaseOfItemCategory(anyInt());
    }

    @Test
    void testGetMonthlyTotalPurchaseOfItemCategory5() {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryName("Category Name");
        itemCategoryModel.setId(new ObjectId());

        ArrayList<ItemCategoryModel> itemCategoryModelList = new ArrayList<>();
        itemCategoryModelList.add(itemCategoryModel);
        when(this.analyticsRepository.getAllCategories()).thenReturn(itemCategoryModelList);
        when(this.analyticsRepository.getMonthlyTotalPurchaseOfItemCategory(anyInt())).thenReturn(new ArrayList<>());
        Map<String, Double> actualMonthlyTotalPurchaseOfItemCategory = this.analyticsService
            .getMonthlyTotalPurchaseOfItemCategory(1);
        assertEquals(1, actualMonthlyTotalPurchaseOfItemCategory.size());
        Double expectedGetResult = new Double(0.0d);
        assertEquals(expectedGetResult, actualMonthlyTotalPurchaseOfItemCategory.get("Category Name"));
        verify(this.analyticsRepository).getAllCategories();
        verify(this.analyticsRepository).getMonthlyTotalPurchaseOfItemCategory(anyInt());
    }
}

