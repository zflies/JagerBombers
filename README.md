------------------------------------------------------------------------
EECS 690 Project - Team JagerBombers
------------------------------------------------------------------------

------------------------------------------------------------------------
Compiling Instructions
------------------------------------------------------------------------
1. Ensure Eclipse supports Swing/Window Builder
2. From the Project Explorer Window in Eclipse, Run DepotDinerPOS as a
   JavaApplication from the file LoginWindow.java.  This will ensure the
   program starts with the Main Login Screen.


------------------------------------------------------------------------
Program Description
------------------------------------------------------------------------
1. The Login Window will serve as the program's security.  Each time an
   employee wishes to interact with this application, they must enter
   their employee specific PIN, with the exception of the Kitchen Staff
   who will share a common PIN for screen access.

   a. After entering a PIN and selecting "Submit," the program will
      distinguise the type of employee you are (i.e. Wait Staff,
      Kitchen Staff, or Manager/Owner)

   b. The PIN is established when an employee is added to the staff by
      a manager.  Please consult the DB Admin if you forgot your PIN.
      
2. Depding on the type of employee, a view will be loaded (i.e. Wait
   Staff View, Kitchen View, and Manager/Owner View).  Once the employee
   is finished using the application, they will hit the "EXIT" button in
   the top right corner to quickly log them out and return to the login
   screen.

   a. The Staff View will contain two columns of features. On the left
      there is a list of all tables that the server is assigned to. If
      a table in this list is selected, the list in the right column
      will update to display table specific information.  Additional
      buttons allow the user to Create Orders, View Order History of
      table that have already payed, Add Orders to specific tables, and
      Process Payments for a specific table.
      
  b.  The Kitchen View will contain a queue like list of orders that need
      to be prepared.  As a meal is finished, an option to remove that
      item from the list will be provided.
      
  c.  The Manager/Owner View will contain a list on the left of all 
      empolyees that work at Steve's Depot Diner with the feature to add
      a new employee.  If an employee is selected, a panel of features on 
      the right side of the page will allow the manager to Clock In/Out
      the employee, view the weekly hours worked of that server, and also
      the option to delete that worker from the system.  The last featur
      will allow the manager to generate a report for the day providing
      a deatailed description of the day.


  
  
  
  
  
  
  
