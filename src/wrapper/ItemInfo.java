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

public class ItemInfo
{
  // This class is not intended to be instantiated
  private ItemInfo()
  {
  }

/*
 * Get the IDs of all the drivers in the database
 */
  public static int[] getItems()
  {
    return database.busDatabase.select_ids("items_id", "items", "items_id");
  }
  
  public static int[] getItemsbyName()
  {
    return database.busDatabase.select_ids("items_id", "items", "name");
  }
/*
 * Find the driver with the specified driver number
 */
  public static int findDriver(String number)
  {
    return database.busDatabase.find_id("driver", "number", number);
  }

/*
 * Get the real name of a driver
 */
  public static String getName(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("items", item, "name");
  }

   
/*
 * Get the number of days holiday taken, or planned to be taken, in the
 * current calendar year
 */
  public static Double getItemsPrice(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_float("items", item, "price");
  }
  public static int getItemsClearStatus(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_int("items", item, "clear");
  }

/*
 * Set the number of days holiday taken, or planned to be taken, in
 * the current calendar year.
 */
  public static void setItemsPrice(int item, int value)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      database.busDatabase.set_value("items", item, "price", value);
  }
  

  
  public static Double getItemsBasePrice(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_float("items", item, "base_price");
  }

  
    public static int getItemsQuantity(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_int("items", item, "quantity");
  }
    
   public static Date getSoldsDate(int sold)
  {
    if (sold == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_date("sold", sold, "date");
  }

/*
 * Set the number of days holiday taken, or planned to be taken, in
 * the current calendar year.
 */
  public static void setItemsQuantity(int item, int value)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      database.busDatabase.set_value("items", item, "quantity", value);
  }
  
  public static void setItemsClearStatus(int item, int value)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      database.busDatabase.set_value("items", item, "clear", value);
  }

/*
 * Get the identification number of a driver
 */
  public static String getNumber(int driver)
  {
    if (driver == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("driver", driver, "number");
  }

/*
 * Determine whether a driver is available on a given date
 */
  public static boolean isAvailable(int driver, Date date)
  {
    if (date == null) 
      throw new InvalidQueryException("Date is null");
    
    if (driver == 0) 
      throw new InvalidQueryException("Nonexistent driver");
    
    database db = database.busDatabase;
    
    if (db.select_record("driver_availability", "driver", driver, "day", date))
      return (Integer)db.get_field("available") != 0;
    else
      return true;
  }
  
/*
 * Determine whether a driver is available today
 */
  public static boolean isAvailable(int driver)
  {
    return isAvailable(driver, database.today());
  }
  
  
   public static int findItemsID(String name)
  {
    return database.busDatabase.find_id("items", "name", name);
  }

/*
 * Set whether a driver is available on a given date
 */
  public static void addItemSold( String name, double price, int quantity, Date date)
  {
      database.busDatabase.new_record("sold", new Object[][]{{"name", name}, {"quantity", quantity}, 
                                      {"price", price},{"date", date}});
  }//setAvailable
  
  public static void addItem( String name, double price, double base_price, int quantity)
  {
      database.busDatabase.new_record("items", new Object[][]{{"name", name}, {"quantity", quantity}, 
                                      {"price", price},{"base_price", base_price}});
  }//setAvailable
  
  public static void addItem( String name, double price,int quantity)
  {

 
      database.busDatabase.new_record("items", new Object[][]{{"name", name}, {"quantity", quantity}, 
                                      {"price", price}});
  }//setAvailable

  

/*
 * Set whether a driver is available today
 */

public static void deleteItems(int items)
  {
    if (items == 0) throw new InvalidQueryException( " does not exist");
      database.busDatabase.delete_record("items", "items_id", items);
  }

  
}//class


