package com.trinhthianh.dataproviders;

import com.trinhthianh.helpers.ExcelHelper;
import com.trinhthianh.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderAddTask {
    @DataProvider(name = "data_provider_add_task")
    public Object[][] dataAddTask() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getDataHashTable(SystemHelper.getCurrentDir() + "DataTest/AddTask.xlsx", "AddTask", 3, 3);
        return data;
    }
}
 