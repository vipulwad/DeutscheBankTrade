package com.vipul.DeutscheBankTrade.Store;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;

import java.sql.*;

public class DataStoreTradeToSqlite extends DataStore {
    private Connection conn = null;

    public DataStoreTradeToSqlite(String connection) {
        super(connection);
        getConnection();
        CreateTradeTable();
    }

    public Connection getConnection() {
        if (conn != null)
            return conn;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connection);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("database successfully created");
        return conn;
    }

    private boolean CreateTradeTable() {
        Statement stmt ;
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Database Opened...\n");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE Trade " +
                    "(TradeId TEXT NOT NULL," +
                    " Version INTEGER NOT NULL, " +
                    " CounterPartyId TEXT, " +
                    " BookId TEXT, " +
                    " MaturityDate TEXT, " +
                    " CreatedDate TEXT, " +
                    " Expired BOOLEAN, " +
                    "PRIMARY KEY (TradeId, Version));";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Trade Table Created Successfully!!!");
        return true;
    }


    public boolean InsertRow(TradeInputData tid) {
        Connection connection = getConnection();
        Statement stmt ;

        try {
            stmt = connection.createStatement();
            String sql ;

            sql = "INSERT INTO Trade (TradeId, Version, CounterPartyId, BookId, MaturityDate, CreatedDate, Expired) " +
                    "VALUES ('" + tid.getTradeId() + "'," +
                    tid.getVersion() + "," +
                    "'" + tid.getCounterPartyId() + "'," +
                    "'" + tid.getBookId() + "'," +
                    "'" + tid.getMaturityDate() + "'," +
                    "'" + tid.getCreatedDate() + "'," +
                    tid.isExpired() + ")";
            System.out.println("Insert SQL : " + sql);
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Failed !!");
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Inserted Successfully!!!");

        return true;
    }

    public boolean UpdateRow(TradeInputData tid) {
        Connection connection = getConnection();
        Statement stmt ;

        try {
            stmt = connection.createStatement();
            String sql ;

            sql = "UPDATE Trade Set TradeId = '" + tid.getTradeId() +
                    "', Version = " + tid.getVersion() +
                    ", CounterPartyId = '" + tid.getCounterPartyId() +
                    "', BookId = '" + tid.getBookId() +
                    "', MaturityDate = '" + tid.getMaturityDate() +
                    "', CreatedDate = '" + tid.getCreatedDate() +
                    "', Expired = " + tid.isExpired() +
                    " WHERE TradeId = '" + tid.getTradeId() +
                    "' AND Version = " + tid.getVersion() + ";";
            System.out.println("UPDATE STATEMENT : " + sql);
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Updated Successfully!!!");
        return true;
    }

    public boolean UpdateAllOldTransactionForTrade(String tradeId, String dateString) {
        Connection connection = getConnection();
        Statement stmt ;

        try {
            stmt = connection.createStatement();
            String sql ;

            sql = "UPDATE Trade Set Expired = " + true +
                    " WHERE TradeId = '" + tradeId +
                    "' AND MaturityDate < '" + dateString +
                    "' AND Expired = " + false + ";";
            System.out.println("UPDATE Expired Transactions : " + sql);
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Updated Successfully!!!");
        return true;
    }

    public TradeInputData getMaxVersionForTrade(String tradeId) {
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        TradeInputData data = new TradeInputData();


        try {
            stmt = connection.createStatement();
            String sql1 = "SELECT TradeId, MAX(Version) Ver FROM Trade Where TradeId = '" + tradeId + "' GROUP BY TradeId";
            System.out.println("Find max trade version Statement: " + sql1);
            rs = stmt.executeQuery(sql1);


            if (!rs.isBeforeFirst()){
                System.out.println("No Old trades found");
                rs.close();
                stmt.close();
                return null;
            }

            String trdId = rs.getString("TradeId");
            int ver = rs.getInt("Ver");
            rs.close();
            stmt.close();

            stmt = connection.createStatement();
            String sql2 = "SELECT TradeId, Version, CounterPartyId, BookId, MaturityDate, CreatedDate, Expired FROM Trade Where TradeId = '" +
                    trdId + "'" + " AND Version = " + ver + ";";
            System.out.println("Select Statement: " + sql2);
            rs = stmt.executeQuery(sql2);
            data.setTradeId(rs.getString("TradeId"));
            data.setVersion(rs.getInt("Version"));
            data.setCounterPartyId(rs.getString("CounterPartyId"));
            data.setBookId(rs.getString("MaturityDate"));
            data.setCreatedDate(rs.getString("CreatedDate"));
            data.setExpired(rs.getBoolean("Expired"));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                rs.close();
                stmt.close();
            } catch (SQLException | NullPointerException e1) {
                e1.printStackTrace();
            }
            return null;
        }
        System.out.println("Successfully fetched data!!!");
        return data;

    }
}