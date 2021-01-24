package wrapper;
import java.util.Date;

 
/*
* Class which represents information about drivers and their availability.
* It also allows the application to get and set the number of hours a
* driver has worked over time periods of a week or a year, and the
* holidays taken by a driver in the current calendar year.<br><br>
*
* As well as an ID, drivers (like buses) have numbers which are traditionally
* used to identify them. You can also get the name of a specified driver<br><br>
*
* The methods contain checks for invalid queries, which will result in
* InvalidQueryExceptions being thrown, but it does not enforce "business
* rules" such as checking for dates in the past.
*/

public class CustomerInfo
{
  // This class is not intended to be instantiated
  private CustomerInfo()
  {
  }

/*
 * Get the IDs of all the drivers in the database
 */
  public static int[] customers()
  {
    return database.busDatabase.select_ids("customers_id", "customers", "customers_id");
  }
  
  public static int[] customersByName()
  {
    return database.busDatabase.select_ids("customers_id", "customers", "name");
  }
/*
 * Find the driver with the specified driver number
 */
  public static int findCustomerID(String name)
  {
    return database.busDatabase.find_id("customers", "name", name);
  }

/*
 * Get the real name of a driver
 */
  public static String getName(int customers)
  {
    if (customers == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("customers", customers, "name");
  }

   
/*
 * Get the number of days holiday taken, or planned to be taken, in the
 * current calendar year
 */
  public static String customerAddress(int customers)
  {
    if (customers == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("customers", customers, "address");
  }
  public static String customerMobile(int customers)
  {
    if (customers == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("customers", customers, "mobile");
  }




/*
 * Set whether a driver is available on a given date
 */
  public static void addInfo( String name, String address, String mobile )
  {
      database.busDatabase.new_record("customers", new Object[][]{{"name", name}, {"address", address}, 
          {"mobile",mobile}});
  }//setAvailable
  
  public static void addDebtID(int debts, String name, String description, int value ,int given, Date date)
  {
      database.busDatabase.new_record("debts", new Object[][]{{"name", name}, {"description", description}, 
          {"debts_id",debts}, {"value",value}, {"given", given},{"date", date}});
  }//setAvailable
  
  public static void addItem( String name, double price,int quantity)
  {

 
      database.busDatabase.new_record("items", new Object[][]{{"name", name}, {"quantity", quantity}, 
                                      {"price", price}});
  }//setAvailable


/*
 * Set whether a driver is available today
 */

 public static void deleteCustomers(int customers)
  {
    if (customers == 0) throw new InvalidQueryException( " does not exist");
      database.busDatabase.delete_record("customers", "customers_id", customers);
  }
}//class
