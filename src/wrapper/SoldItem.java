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

public class SoldItem
{
  // This class is not intended to be instantiated
  private SoldItem()
  {
  }

/*
 * Get the IDs of all the drivers in the database
 */
  public static int[] getItems()
  {
    return database.busDatabase.select_ids("solds_id", "sold", "sold_id");
  }
  
  public static int[] getItemsbyDate()
  {
    return database.busDatabase.select_ids("solds_id", "solds", "date");
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
  public static String getName(int solds)
  {
    if (solds == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("solds", solds, "name");
  }

   
/*
 * Get the number of days holiday taken, or planned to be taken, in the
 * current calendar year
 */
  public static Double getItemsPrice(int solds)
  {
    if (solds == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_float("solds",solds, "price");
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
      database.busDatabase.set_value("solds", item, "price", value);
  }

  
    public static int getItemsQuantity(int solds)
  {
    if (solds == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_int("solds", solds, "quantity");
  }
    
     public static int getItemsID(int item)
  {
    if (item == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_int("solds", item, "items_id");
  }
    
   public static Date getSoldsDate(int solds)
  {
    if (solds == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_date("solds", solds, "date");
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
  

/*
 * Set whether a driver is available on a given date
 */
  public static void addItem(String name,int item ,double price, int quantity, Date date)
  {
  database.busDatabase.new_record("solds", new Object[][]{{"items_id", item},{"name", name}, {"quantity", quantity}, 
                                      {"price", price},{"date", date}});
  }//setAvailable
  
  public static void addItem( String name, double price,int quantity)
  {

 
      database.busDatabase.new_record("solds", new Object[][]{{"name", name}, {"quantity", quantity}, 
                                      {"price", price}});
  }//setAvailable

  public static void deleteSold(int solds)
  {
    if (solds == 0) throw new InvalidQueryException( " does not exist");
      database.busDatabase.delete_record("solds", "solds_id", solds);
  }

/*
 * Set whether a driver is available today
 */


}//class
