/*
 Navicat Premium Data Transfer

 Source Server         : localhost-27017
 Source Server Type    : MongoDB
 Source Server Version : 40002
 Source Host           : localhost:27017
 Source Schema         : blog

 Target Server Type    : MongoDB
 Target Server Version : 40002
 File Encoding         : 65001

 Date: 10/06/2019 10:10:15
*/


// ----------------------------
// Collection structure for user
// ----------------------------
db.getCollection("user").drop();
db.createCollection("user");

// ----------------------------
// Documents of user
// ----------------------------
db.getCollection("user").insert([ {
    _id: ObjectId("5cd536f6126a6d3fc8631174"),
    realName: "超级管理员",
    username: "admin",
    password: "$2a$10$BjsCzrbjmSQXWBIo6e/WbeAhqjM.cUpXJ33cIO1Oujgm4K5wrzNqK",
    email: "123@qq.com",
    status: "1",
    _class: "com.miao.boot.blog.domain.User"
} ]);
