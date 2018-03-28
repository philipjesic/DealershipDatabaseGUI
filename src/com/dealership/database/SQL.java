package com.dealership.database;

import java.sql.*;

import static java.sql.Types.DATE;
import static java.sql.Types.INTEGER;

public class SQL {
    static String url = "jdbc:oracle:thin:@localhost:1521:ug";

    static String username = "ora_q8c1b";
    static String password = "a52538162";
    public static Connection con = null;

    public static void getConnection()
            throws SQLException {
        // Call another method to establish the connection
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection(
                url, username, password);
    }

    // Querey example to get unsold cars
    public static void getUnsoldCarsGUI(Connection con)
            throws SQLException {
        // Here is the template how to get Unsold Card
        ResultSet rs = getUnsoldCars(con, "Sold");
        printDataGUI(rs);
        rs.close();
    }

    // Querey example to get receptionist appointments gui
    public static void getReceptionstAppointmentsGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getReceptionstAppointments(con, 7843);
        printDataGUI(rs);
        rs.close();
    }

    public static void getCustomerAppointmentsGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getCustomerAppointments(con, 68444 );
        printDataGUI(rs);
        rs.close();
    }
    //querey example to get managers based on emploment status
    public static void getEmployedStatusSManagersGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getEmployedStatusSManagers(con, "Employed");
        printDataGUI(rs);
        rs.close();
    }

    public static void getAllServicedCarsGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getAllServicedCars(con);
        printDataGUI(rs);
        rs.close();
    }

    public static void getAveragePriceOfUnsoldCarsGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getAveragePriceOfUnsoldCars(con);
        printDataGUI(rs);
        rs.close();
    }

    public static void getNumberOfCarsForSaleGUI(Connection con)
            throws SQLException {
        // Here is the template how to get receptionist appointments
        ResultSet rs = getNumberOfCarsForSale(con);
        printDataGUI(rs);
        rs.close();
    }

    public static void getAveragePriceByMakeGUI(Connection con )
            throws SQLException {
        ResultSet rs = getAveragePriceByMakeView(con);
        printDataGUI(rs);
        rs.close();
    }

    // Print the ResultSet Template - works for every table - reusable
    public static void printDataGUI(ResultSet rs)
            throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int numberOfColumns = rsMetaData.getColumnCount();
        String header = "";
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = rsMetaData.getColumnName(i);
            header = header + columnName + " \t";
        }
        System.out.println(header);
        while (rs.next()) {
            String row = "";
            for (int k = 1; k <= numberOfColumns; k++){
                String columnClassName = rsMetaData.getColumnClassName(k);
                Object o = rs.getObject(k);
                row = row + o.toString() + " \t";
            }
            System.out.println(row);
        }
    }

    // Middle Tier goes here add new methods/Queries
    // First method - get all cars based on the sold status
    public static ResultSet getUnsoldCars(Connection con, String isSold)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select c.vin, c.mileage, c.year," +
                "c.model, c.make,c.trim from car c where isSold like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1, isSold + "%");
        rs = ps.executeQuery();
        return rs;
    }

    // Second method - get all cars based on the sold status
    public static ResultSet getReceptionstAppointments(Connection con, int id)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from BOOKED_APPOINTMENT where RECEPTIONISTID = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getCustomerAppointments(Connection con, int id)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from BOOKED_APPOINTMENT where CUSTOMERID = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getAvailableAppointments(Connection con)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from APPOINTMENT";
        PreparedStatement ps = con.prepareStatement(selectSql);
        rs = ps.executeQuery();
        return rs;
    }

    public static void insertNewCustomer(Connection con, int cid, int phonenum,
                                              String firstName, String lastName)
                        throws SQLException {

        String selectSql = "INSERT INTO Customer " +
                "(customerid, phonenumber, f_name,l_name) " +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,cid);
        ps.setInt(2,phonenum);
        ps.setString(3,firstName);
        ps.setString(4,lastName);
        ps.executeUpdate();
        con.commit();
    }

    public static ResultSet getCustomer(Connection con, int cid)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from customer where customerid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, cid);
        rs = ps.executeQuery();
        return rs;
    }

    public static void insertNewAppointmentSpace(Connection con, java.sql.Date date, String time)
            throws SQLException {

        String selectSql = "INSERT INTO appointment " +
                "(appt_date, appt_time) " +
                " VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setDate(1,date);
        ps.setString(2,time);
        ps.executeUpdate();
        con.commit();
    }

    public static ResultSet getAppointmentSpace(Connection con, java.sql.Date date,String time)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from Appointment where appt_date = ? AND appt_time like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setDate(1, date);
        ps.setString(2,time + "%");
        rs = ps.executeQuery();
        return rs;
    }

    public static void insertNewBookedAppointment(Connection con, java.sql.Date date, String time,
                                                  int customerID, int receptionistID)
            throws SQLException {

        String selectSql = "INSERT INTO booked_appointment " +
                "(appt_date, appt_time, customerid, RECEPTIONISTID) " +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setDate(1,date);
        ps.setString(2,time);
        ps.setInt(3,customerID);
        ps.setInt(4,receptionistID);
        ps.executeUpdate();
        con.commit();
    }

    public static ResultSet getBookedAppointment(Connection con, java.sql.Date date,String time,
                                                 int customerID, int receptionistID)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from Booked_Appointment where appt_date = ? AND appt_time like ?" +
                "AND customerid = ? AND RECEPTIONISTID = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setDate(1, date);
        ps.setString(2,time + "%");
        ps.setInt(3,customerID);
        ps.setInt(4,receptionistID);
        rs = ps.executeQuery();
        return rs;
    }

    public static void deleteReceptionist(Connection con, int receptionistID)
            throws SQLException {

        String deleteSQL = "DELETE Receptionist WHERE staffid = ?";
        PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, receptionistID);
        // execute delete SQL stetement
        preparedStatement.executeUpdate();
    }

    public static ResultSet getReceptionist(Connection con, int receptionistID)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from receptionist where staffid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,receptionistID);
        rs = ps.executeQuery();
        return rs;
    }




    public static ResultSet getEmployedStatusSManagers(Connection con, String status)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from MANAGER where WORK_STATUS like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1,  status + "%");
        rs = ps.executeQuery();
        return rs;
    }
    public static ResultSet getAllServicedCars(Connection con)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "SELECT *\n" +
                "FROM Car C\n" +
                "WHERE NOT EXISTS\n" +
                "((SELECT S.ServiceName\n" +
                "FROM ServiceType S)\n" +
                "MINUS\n" +
                "(SELECT S1.ServiceName\n" +
                "FROM ServiceRecord SR, Service S1      \n" +
                "WHERE C.vin = SR.vin AND SR.ServiceId = S1.ServiceId AND SR.OrderNumber = S1.OrderNumber))";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

    public static void insertServiceRecord(Connection con, int vin, int mid, int sid, int ord, Date date,
                                                int hrs, int price, int miles)
            throws SQLException {

        String selectSql = "INSERT INTO ServiceRecord " +
                "(vin, MechanicId, ServiceId,OrderNumber,Service_Date,Hours, Price,RecordedMileage) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,vin);
        ps.setInt(2,mid);
        ps.setInt(3,sid);
        ps.setInt(4,ord);
        ps.setDate(5,date);
        ps.setInt(6,hrs);
        ps.setInt(7,price);
        ps.setInt(8,miles);
        ps.executeUpdate();
        con.commit();
    }

    public static ResultSet getServiceRecordForVin(Connection con, int vin)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from ServiceRecord where vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, vin);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getSalesRecordForCar(Connection con, int vin)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from car_for_sale where vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, vin);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getCarsInPriceRange(Connection con, int minPrice, int maxPrice)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "Select c.VIN,c.MAKE,c.MODEL,cfs.listprice,cfs.finalprice,cfs.solddate " +
                "from car_for_sale cfs Right Outer Join car c ON c.vin = cfs.vin " +
                "where cfs.listprice > ?  AND cfs.listprice < ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, minPrice);
        ps.setInt(2, maxPrice);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getCarsInMileageRange(Connection con, int minKm, int maxKm)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "Select c.VIN,c.MAKE,c.MODEL,cfs.listprice,cfs.finalprice,cfs.solddate " +
                "from car_for_sale cfs Right Outer Join car c ON c.vin = cfs.vin " +
                "where c.mileage > ?  AND c.mileage < ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, minKm);
        ps.setInt(2, maxKm);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getMechanicsByLastName(Connection con, String lastname)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from mechanic where l_name like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1, lastname + "%");
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getReceptionistByLastName(Connection con, String lastname)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from receptionist where l_name like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1, lastname + "%");
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getManagerByLastName(Connection con, String lastname)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from manager where l_name like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1, lastname + "%");
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getSalesmenByLastName(Connection con, String lastname)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from salesmen where l_name like ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setString(1, lastname + "%");
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getMechanicsByID(Connection con, int staffId)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from mechanic where staffid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, staffId);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getReceptionistByID(Connection con, int staffId)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from receptionist where staffid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, staffId);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getManagerByID(Connection con, int staffId)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from manager where staffid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, staffId);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getSalesmenByID(Connection con, int staffId)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from salesmen where staffid = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, staffId);
        rs = ps.executeQuery();
        return rs;
    }


    public static ResultSet getAveragePriceOfUnsoldCars(Connection con)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = " SELECT AVG(cfs.ListPrice)\n" +
                "FROM Car_for_sale cfs, car c\n" +
                "WHERE cfs.vin = c.vin AND c.isSold = 'Not Sold'";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

    public static ResultSet getCarRecord(Connection con, int vin)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from car where vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, vin);
        rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet getSalesRecord(Connection con, int vin)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "select * from car_for_sale where vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1, vin);
        rs = ps.executeQuery();
        return rs;
    }

    public static void insertMechanic(Connection con, int id, Date dob, String fname, String lname, String city,
                                      int dealershipnumber,String workstatus)
            throws SQLException {

        String selectSql = "INSERT INTO Mechanic " +
                "(staffid, dateofbirth, f_name,l_name,city,dealershipnumber, work_status) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,id);
        ps.setDate(2,dob);
        ps.setString(3,fname);
        ps.setString(4,lname);
        ps.setString(5,city);
        ps.setInt(6,dealershipnumber);
        ps.setString(7,workstatus);
        ps.executeUpdate();
        con.commit();
    }

    public static void insertReceptionist(Connection con, int id, Date dob, String fname, String lname, String city,
                                      int dealershipnumber,String workstatus)
            throws SQLException {

        String selectSql = "INSERT INTO receptionist " +
                "(staffid, dateofbirth, f_name,l_name,city,dealershipnumber, work_status) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,id);
        ps.setDate(2,dob);
        ps.setString(3,fname);
        ps.setString(4,lname);
        ps.setString(5,city);
        ps.setInt(6,dealershipnumber);
        ps.setString(7,workstatus);
        ps.executeUpdate();
        con.commit();
    }
    public static void insertSalesmen(Connection con, int id, Date dob, String fname, String lname, String city,
                                      int dealershipnumber,String workstatus)
            throws SQLException {

        String selectSql = "INSERT INTO salesmen " +
                "(staffid, dateofbirth, f_name,l_name,city,dealershipnumber, work_status) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,id);
        ps.setDate(2,dob);
        ps.setString(3,fname);
        ps.setString(4,lname);
        ps.setString(5,city);
        ps.setInt(6,dealershipnumber);
        ps.setString(7,workstatus);
        ps.executeUpdate();
        con.commit();
    }

    public static void insertManager(Connection con, int id, Date dob, String fname, String lname, String city,
                                      int dealershipnumber,String workstatus)
            throws SQLException {

        String selectSql = "INSERT INTO manager " +
                "(staffid, dateofbirth, f_name,l_name,city,dealershipnumber, work_status) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,id);
        ps.setDate(2,dob);
        ps.setString(3,fname);
        ps.setString(4,lname);
        ps.setString(5,city);
        ps.setInt(6,dealershipnumber);
        ps.setString(7,workstatus);
        ps.executeUpdate();
        con.commit();
    }

    public static void insertCarFeatures(Connection con, int year,String model,String make, String trim, String features)
            throws SQLException {

        String selectSql = "INSERT INTO CAR_SPECS " +
                "(year,model,make,trim,features) " +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,year);
        ps.setString(2,model);
        ps.setString(3,make);
        ps.setString(4,trim);
        ps.setString(5,features);
        ps.executeUpdate();
        con.commit();
    }

    public static void insertCar(Connection con, int vin, int mileage, String isSold, String prevStatus, String title,
                                     int year,String model,String make, String trim)
            throws SQLException {

        String selectSql = "INSERT INTO CAR " +
                "(vin, mileage, isSold, previousstatus, titlestatus, year, model, make, trim) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,vin);
        ps.setInt(2,mileage);
        ps.setString(3,isSold);
        ps.setString(4,prevStatus);
        ps.setString(5,title);
        ps.setInt(6,year);
        ps.setString(7,model);
        ps.setString(8,make);
        ps.setString(9,trim);
        ps.executeUpdate();
        con.commit();
    }



    public static void insertSaleRecord(Connection con, int vin, int listprice)
            throws SQLException {

        String selectSql = "INSERT INTO CAR_FOR_SALE " +
                "(vin, listprice, solddate, finalPrice, customerId, managerid, " +
                "salesmenid) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,vin);
        ps.setInt(2,listprice);
        ps.setNull(3, DATE);
        ps.setNull(4, Types.INTEGER);
        ps.setNull(5, INTEGER);
        ps.setNull(6,INTEGER);
        ps.setNull(7,INTEGER);

        ps.executeUpdate();
        con.commit();
    }

    public static void updateListPrice(Connection con, int vin, int listprice)
            throws SQLException {

        String selectSql = "UPDATE CAR_FOR_SALE SET " +
                "listprice = ? WHERE vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,listprice);
        ps.setInt(2,vin);

        ps.executeUpdate();
        con.commit();
    }

    public static void updateisSold(Connection con, int vin)
            throws SQLException {

        String selectSql = "UPDATE CAR SET " +
                "issold = 'Sold' WHERE vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setInt(1,vin);

        ps.executeUpdate();
        con.commit();
    }
    public static void updateSoldCar(Connection con, int vin, java.sql.Date soldDate, int finalprice,
                                     int customerID, int managerID, int salesmenID)
            throws SQLException {

        String selectSql = "UPDATE CAR_FOR_SALE SET " +
                "solddate = ?, finalprice = ? , customerid = ? , managerid = ? , salesmenid = ? WHERE vin = ?";
        PreparedStatement ps = con.prepareStatement(selectSql);
        ps.setDate(1,soldDate);
        ps.setInt(2,finalprice);
        ps.setInt(3,customerID);
        ps.setInt(4,managerID);
        ps.setInt(5,salesmenID);
        ps.setInt(6,vin);

        ps.executeUpdate();
        con.commit();
    }


    public static ResultSet getNumberOfCarsForSale(Connection con)
            throws SQLException {

        ResultSet rs = null;
        String selectSql = "SELECT COUNT (c.vin)\n" +
                "FROM car c\n" +
                "WHERE c.isSold = 'Not Sold'";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }
    public static ResultSet getAveragePriceByMakeView(Connection con)
            throws SQLException {
        ResultSet rs = null;
        String selectSql = "\n" +
                "CREATE VIEW AverageBrandPrice(Make, Average) as\n" +
                "    SELECT C.make, AVG(CFS.ListPrice) AS Average\n" +
                "    FROM Car C, Car_For_Sale CFS\n" +
                "    WHERE C.vin = CFS.vin AND C.isSold = 'Not Sold'\n" +
                "    GROUP BY C.make";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

    public static ResultSet getMostExpensiveBrand(Connection con)
        throws SQLException {
        ResultSet rs = null;
        String selectSql = "Select Make, Average  From averagebrandprice " +
                "Where average = (select MAX(average) from averagebrandprice)";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

    public static ResultSet getLeastExpensiveBrand(Connection con)
            throws SQLException {
        ResultSet rs = null;
        String selectSql = "Select Make, Average  From averagebrandprice " +
                "Where average = (select MIN(average) from averagebrandprice)";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

    public static ResultSet getDealerships(Connection con)
            throws SQLException {
        ResultSet rs = null;
        String selectSql = "Select *  From dealership ";
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(selectSql);
        return rs;
    }

}
