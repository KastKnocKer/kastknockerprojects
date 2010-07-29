package gestionale.client;
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.DataSourceFloatField;  
import com.smartgwt.client.data.fields.DataSourceIntegerField;  
import com.smartgwt.client.data.fields.DataSourceTextField;  
import com.smartgwt.client.widgets.grid.ListGridRecord;
  
public class EmployeeXmlDS extends DataSource {  
  
    private static EmployeeXmlDS instance = null;  
  
    public static EmployeeXmlDS getInstance() {  
        if (instance == null) {  
            instance = new EmployeeXmlDS("employeesDS");  
        }  
        return instance;  
    }  
  
    public EmployeeXmlDS(String id) {  
  
        setID(id);  
        setTitleField("Name"); 
        DataSourceTextField nameField = new DataSourceTextField("Name", "Name");  
  
        DataSourceIntegerField employeeIdField = new DataSourceIntegerField("EmployeeId", "Employee ID");  
        employeeIdField.setPrimaryKey(true);  
        employeeIdField.setRequired(true);  
  
        DataSourceIntegerField reportsToField = new DataSourceIntegerField("ReportsTo", "Manager");  
        reportsToField.setRequired(true);  
        reportsToField.setForeignKey(id + ".EmployeeId");  
        reportsToField.setRootValue("1");  
  
        setFields(nameField, employeeIdField, reportsToField);  
  
        setClientOnly(true);  
        
        
        for(int i=1; i<15; i++){
        	ListGridRecord lgr = new ListGridRecord();
        	lgr.setAttribute("EmployeeId", Integer.toString(i+1));
        	lgr.setAttribute("ReportsTo", Integer.toString(i));
        	lgr.setAttribute("Name", Integer.toString(i+1));
        	this.addData(lgr);
        }
    }  
}  