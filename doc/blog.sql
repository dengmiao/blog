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

 Date: 03/07/2019 17:50:42
*/


// ----------------------------
// Collection structure for permission
// ----------------------------
db.getCollection("permission").drop();
db.createCollection("permission");

// ----------------------------
// Documents of permission
// ----------------------------
db.getCollection("permission").insert([ {
    _id: ObjectId("5d19d32acf743e335846a157"),
    pid: "",
    name: "test",
    icon: "&#xe6b8;",
    type: "0",
    routing: "",
    sort: NumberInt("-1"),
    isBlank: NumberInt("1"),
    _class: "com.miao.boot.blog.domain.Permission"
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ab94537510000a1001aa2"),
    pid: "0",
    name: "会员管理",
    type: NumberLong("0"),
    icon: "&#xe6b8;",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ab9bb37510000a1001aa3"),
    pid: "5d1ab94537510000a1001aa2",
    name: "统计页面",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "welcome1.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1aba1f37510000a1001aa4"),
    pid: "5d1ab94537510000a1001aa2",
    name: "会员列表(静态表格)",
    icon: "&#xe6a7;",
    routing: "member-list.html",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1aba2237510000a1001aa5"),
    pid: "5d1ab94537510000a1001aa2",
    type: NumberLong("0"),
    name: "会员列表(动态表格)",
    icon: "&#xe6a7;",
    routing: "member-list1.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abab137510000a1001aa6"),
    pid: "5d1ab94537510000a1001aa2",
    type: NumberLong("0"),
    name: "会员删除",
    icon: "&#xe6a7;",
    routing: "member-del.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 4
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abae537510000a1001aa7"),
    pid: "5d1ab94537510000a1001aa2",
    name: "会员管理",
    icon: "&#xe70b;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 5
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abbfa37510000a1001aa8"),
    pid: "5d1abae537510000a1001aa7",
    name: "会员删除",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "member-del.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abc3537510000a1001aa9"),
    pid: "5d1abae537510000a1001aa7",
    name: "等级管理",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "member-list1.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abc7237510000a1001aaa"),
    name: "订单管理",
    pid: "0",
    icon: "&#xe723;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abc9b37510000a1001aab"),
    pid: "5d1abc7237510000a1001aaa",
    name: "订单列表",
    icon: "&#xe6a7;",
    routing: "order-list.html",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abce437510000a1001aac"),
    pid: "5d1abc7237510000a1001aaa",
    name: "订单列表1",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "order-list1.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abd0437510000a1001aad"),
    name: "分类管理",
    pid: "0",
    icon: "&#xe723;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abd3b37510000a1001aae"),
    pid: "5d1abd0437510000a1001aad",
    name: "多级分类",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "cate.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abd7837510000a1001aaf"),
    name: "城市联动",
    pid: "0",
    icon: "&#xe723;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abd9037510000a1001ab0"),
    pid: "5d1abd7837510000a1001aaf",
    name: "三级地区联动",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "city.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abdbd37510000a1001ab1"),
    name: "管理员管理",
    pid: "0",
    icon: "&#xe726;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 4
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abded37510000a1001ab2"),
    pid: "5d1abdbd37510000a1001ab1",
    name: "管理员列表",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "admin-list.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abe1337510000a1001ab3"),
    pid: "5d1abded37510000a1001ab2",
    name: "角色管理",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "admin-role.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abe5237510000a1001ab4"),
    pid: "5d1abe1337510000a1001ab3",
    name: "权限分类",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "admin-cate.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abe6837510000a1001ab5"),
    pid: "5d1abe5237510000a1001ab4",
    name: "权限管理",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "admin-rule.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abea737510000a1001ab6"),
    pid: "0",
    name: "系统统计",
    icon: "&#xe6ce;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 5
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abeb737510000a1001ab7"),
    pid: "5d1abea737510000a1001ab6",
    name: "折线图",
    icon: "&#xe6a7;",
    routing: "echarts1.html",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abedf37510000a1001ab8"),
    pid: "5d1abea737510000a1001ab6",
    name: "折线图",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "echarts2.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf0037510000a1001ab9"),
    pid: "5d1abea737510000a1001ab6",
    name: "地图",
    routing: "echarts3.html",
    type: NumberLong("0"),
    icon: "&#xe6a7;",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf2737510000a1001aba"),
    name: "饼图",
    pid: "5d1abea737510000a1001ab6",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "echarts4.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf5b37510000a1001abb"),
    pid: "5d1abea737510000a1001ab6",
    name: "雷达图",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "echarts5.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 4
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf7437510000a1001abc"),
    pid: "5d1abea737510000a1001ab6",
    name: "k线图",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "echarts6.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 5
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf9237510000a1001abd"),
    pid: "5d1abea737510000a1001ab6",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    name: "热力图",
    routing: "echarts7.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 6
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abf9437510000a1001abe"),
    pid: "5d1abea737510000a1001ab6",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    name: "仪表图",
    routing: "echarts8.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 7
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abfd137510000a1001abf"),
    pid: "0",
    name: "图标字体",
    icon: "&#xe6b4;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 6
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1abfe137510000a1001ac0"),
    pid: "5d1abfd137510000a1001abf",
    name: "图标对应字体",
    routing: "unicode.html",
    type: NumberLong("0"),
    icon: "&#xe6a7;",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac00637510000a1001ac1"),
    pid: "0",
    name: "其它页面",
    icon: "&#xe6b4;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 7
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac06537510000a1001ac2"),
    pid: "5d1ac00637510000a1001ac1",
    name: "登录页面",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "login.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0,
    isBlank: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac0bc37510000a1001ac3"),
    pid: "5d1ac00637510000a1001ac1",
    name: "错误页面",
    icon: "&#xe6a7;",
    routing: "error.html",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac0db37510000a1001ac4"),
    pid: "5d1ac00637510000a1001ac1",
    name: "示例页面",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "demo.html",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac0f537510000a1001ac5"),
    pid: "5d1ac00637510000a1001ac1",
    name: "更新日志",
    routing: "log.html",
    type: NumberLong("0"),
    icon: "&#xe6a7;",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac10f37510000a1001ac6"),
    pid: "0",
    name: "第三方组件",
    icon: "&#xe6b4;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 8
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac12f37510000a1001ac7"),
    pid: "5d1ac10f37510000a1001ac6",
    name: "滑块验证",
    routing: "https://fly.layui.com/extend/sliderVerify/",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 0
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac17b37510000a1001ac8"),
    pid: "5d1ac10f37510000a1001ac6",
    name: "富文本编辑器",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "https://fly.layui.com/extend/layedit/",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 1
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac19a37510000a1001ac9"),
    pid: "5d1ac10f37510000a1001ac6",
    name: "eleTree 树组件",
    routing: "https://fly.layui.com/extend/eleTree/",
    type: NumberLong("0"),
    icon: "&#xe6a7;",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 2
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac1bb37510000a1001aca"),
    name: "图片截取",
    pid: "5d1ac10f37510000a1001ac6",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "https://fly.layui.com/extend/croppers/",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 3
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac1d737510000a1001acb"),
    name: "formSelects 4.x 多选框",
    pid: "5d1ac10f37510000a1001ac6",
    icon: "&#xe6a7;",
    routing: "https://fly.layui.com/extend/formSelects/",
    type: NumberLong("0"),
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 4
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac1f437510000a1001acc"),
    pid: "5d1ac10f37510000a1001ac6",
    name: "Magnifier 放大镜",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "https://fly.layui.com/extend/Magnifier/",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 5
} ]);
db.getCollection("permission").insert([ {
    _id: ObjectId("5d1ac21037510000a1001acd"),
    pid: "5d1ac10f37510000a1001ac6",
    name: "notice 通知控件",
    icon: "&#xe6a7;",
    type: NumberLong("0"),
    routing: "https://fly.layui.com/extend/notice/",
    _class: "com.miao.boot.blog.domain.Permission",
    sort: 6
} ]);

// ----------------------------
// Collection structure for role
// ----------------------------
db.getCollection("role").drop();
db.createCollection("role");

// ----------------------------
// Documents of role
// ----------------------------
db.getCollection("role").insert([ {
    _id: ObjectId("5d1b00d5cf743e274cf32683"),
    code: "ADMIN",
    name: "超级管理员",
    permissions: [
        "5d19d32acf743e335846a157",
        "5d1ab94537510000a1001aa2",
        "5d1ab9bb37510000a1001aa3",
        "5d1aba1f37510000a1001aa4",
        "5d1aba2237510000a1001aa5",
        "5d1abab137510000a1001aa6",
        "5d1abae537510000a1001aa7",
        "5d1abbfa37510000a1001aa8",
        "5d1abc3537510000a1001aa9",
        "5d1abc7237510000a1001aaa",
        "5d1abc9b37510000a1001aab",
        "5d1abce437510000a1001aac",
        "5d1abd0437510000a1001aad",
        "5d1abd3b37510000a1001aae",
        "5d1abd7837510000a1001aaf",
        "5d1abd9037510000a1001ab0",
        "5d1abdbd37510000a1001ab1",
        "5d1abded37510000a1001ab2",
        "5d1abe1337510000a1001ab3",
        "5d1abe5237510000a1001ab4",
        "5d1abe6837510000a1001ab5",
        "5d1abea737510000a1001ab6",
        "5d1abeb737510000a1001ab7",
        "5d1abedf37510000a1001ab8",
        "5d1abf0037510000a1001ab9",
        "5d1abf2737510000a1001aba",
        "5d1abf5b37510000a1001abb",
        "5d1abf7437510000a1001abc",
        "5d1abf9237510000a1001abd",
        "5d1abf9437510000a1001abe",
        "5d1abfd137510000a1001abf",
        "5d1abfe137510000a1001ac0",
        "5d1ac00637510000a1001ac1",
        "5d1ac06537510000a1001ac2",
        "5d1ac0bc37510000a1001ac3",
        "5d1ac0db37510000a1001ac4",
        "5d1ac0f537510000a1001ac5",
        "5d1ac10f37510000a1001ac6",
        "5d1ac12f37510000a1001ac7",
        "5d1ac17b37510000a1001ac8",
        "5d1ac19a37510000a1001ac9",
        "5d1ac1bb37510000a1001aca",
        "5d1ac1d737510000a1001acb",
        "5d1ac1f437510000a1001acc",
        "5d1ac21037510000a1001acd"
    ],
    _class: "com.miao.boot.blog.domain.Role"
} ]);

// ----------------------------
// Collection structure for user
// ----------------------------
db.getCollection("user").drop();
db.createCollection("user");

// ----------------------------
// Documents of user
// ----------------------------
db.getCollection("user").insert([ {
    _id: ObjectId("5d1b0171cf743e266021c827"),
    username: "admin",
    password: "$2a$10$h0xSMGkEC0DLOhcallxawOB6cjWqUL5KGU7AAnG.hL/WyaC/NIr8a",
    realName: "超级管理员",
    email: "admin@163.com",
    status: NumberInt("1"),
    roles: [
        "5d1b00d5cf743e274cf32683"
    ],
    _class: "com.miao.boot.blog.domain.User"
} ]);
