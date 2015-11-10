package com.icfcc.db.dal.mongo;

import com.mongodb.DB;
import com.mongodb.MongoException;


public interface MongoDB {


	DB getDB() throws MongoException;


	DB getDB(String username, String password) throws MongoException;

}