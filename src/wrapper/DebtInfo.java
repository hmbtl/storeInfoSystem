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

public class DebtInfo
{
  // This class is not intended to be instantiated
  private DebtInfo()
  {
  }

/*
 * Get the IDs of all the drivers in the database
 */
  public static int[] getDebts()
  {
    return database.busDatabase.select_ids("debts_id", "debts", "debts_id");
  }
  
  public static int[] getDebtsbyName()
  {
    return database.busDatabase.select_ids("debts_id", "debts", "name");
  }
/*
 * Find the driver with the specified driver number
 */
  public static int findDebtsID(String name)
  {
    return database.busDatabase.find_id("debts", "name", name);
  }

/*
 * Get the real name of a driver
 */
  public static String getName(int debts)
  {
    if (debts == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("debts", debts, "name");
  }

   
/*
 * Get the number of days holiday taken, or planned to be taken, in the
 * current calendar year
 */
  public static String getDebtsDescription(int debts)
  {
    if (debts == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("debts", debts, "description");
  }
  public static int getDebtsGivenStatus(int debts)
  {
    if (debts == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_int("debts", debts, "given");
  }

/*
 * Set the number of days holiday taken, or planned to be taken, in
 * the current calendar year.
 */
  public static void setItemsGivenStatus(int debts, int value)
  {
    if (debts == 0) throw new InvalidQueryException("Nonexistent driver");
      database.busDatabase.set_value("debts", debts, "given", value);
  }
  
/*
 * Get the identification number of a driver
 */
  public static String getNumber(int driver)
  {
    if (driver == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_string("driver", driver, "number");
  }

   public static Date getDebtsDate(int debts)
  {
    if (debts == 0) throw new InvalidQueryException("Nonexistent driver");
      return database.busDatabase.get_date("debts", debts, "date");
  }

/*
 * Set whether a driver is available on a given date
 */
  public static void addDebt( String name, String description, int value ,int given, Date date)
  {
      database.busDatabase.new_record("debts", new Object[][]{{"name", name}, {"description", description}, 
          {"value",value}, {"given", given},{"date", date}});
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

 public static void deleteDebts(int debts)
  {
    if (debts == 0) throw new InvalidQueryException( " does not exist");
      database.busDatabase.delete_record("debts", "debts_id", debts);
  }
}//class
