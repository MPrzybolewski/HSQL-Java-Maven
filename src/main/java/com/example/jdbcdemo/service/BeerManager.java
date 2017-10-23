package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Beer;

public class BeerManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableBeer = "CREATE TABLE Beer(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20), type varchar(30), percentOfAlcohol double, price double)";

	private PreparedStatement addBeerStmt;
	private PreparedStatement deleteAllBeersStmt;
	private PreparedStatement getAllBeersStmt;

	private Statement statement;

	public BeerManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Beer".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableBeer);

			addBeerStmt = connection
					.prepareStatement("INSERT INTO Beer (name, type, percentOfAlcohol, price) VALUES (?, ?, ?, ?)");
			deleteAllBeersStmt = connection
					.prepareStatement("DELETE FROM Beer");
			getAllBeersStmt = connection
					.prepareStatement("SELECT id, name, type, percentOfAlcohol, price FROM Beer");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearBeers() {
		try {
			deleteAllBeersStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addBeer(Beer beer) {
		int count = 0;
		try {
			addBeerStmt.setString(1, beer.getName());
			addBeerStmt.setString(2, beer.getType());
			addBeerStmt.setDouble(3, beer.getPercentOfAlcohol());
			addBeerStmt.setDouble(4, beer.getPrice());

			count = addBeerStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Beer> getAllBeers() {
		List<Beer> beers = new ArrayList<Beer>();

		try {
			ResultSet rs = getAllBeersStmt.executeQuery();

			while (rs.next()) {
				Beer b = new Beer();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setType(rs.getString("type"));
				b.setPercentOfAlcohol(rs.getDouble("percentOfAlcohol"));
				b.setPrice(rs.getDouble("price"));
				beers.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beers;
	}

}
