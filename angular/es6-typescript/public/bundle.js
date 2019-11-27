(function(){function r(e,n,t){function o(i,f){if(!n[i]){if(!e[i]){var c="function"==typeof require&&require;if(!f&&c)return c(i,!0);if(u)return u(i,!0);var a=new Error("Cannot find module '"+i+"'");throw a.code="MODULE_NOT_FOUND",a}var p=n[i]={exports:{}};e[i][0].call(p.exports,function(r){var n=e[i][1][r];return o(n||r)},p,p.exports,r,e,n,t)}return n[i].exports}for(var u="function"==typeof require&&require,i=0;i<t.length;i++)o(t[i]);return o}return r})()({1:[function(require,module,exports){
"use strict";
// access the wowify as a module
// import * as wowify from './wowify';
var __makeTemplateObject = (this && this.__makeTemplateObject) || function (cooked, raw) {
    if (Object.defineProperty) { Object.defineProperty(cooked, "raw", { value: raw }); } else { cooked.raw = raw; }
    return cooked;
};
Object.defineProperty(exports, "__esModule", { value: true });
// access exported function only
var wowify_1 = require("./wowify");
// import {default as wowify} from './wowify';
// import wowify from './wowify';
// destructuringObject();
// deepDestructuringObject();
// destructuringArray();
// restOperator();
// processTaggedTemplate();
// enhancedLoop();
var names = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
console.log(wowify_1.wowify.apply(void 0, names));
function destructuringObject() {
    var addresses = {
        streetAddress1: 'jhamshikhel',
        streetAddress2: 'green house',
        city: 'Lalitpur',
        state: 'Bagmati',
        country: 'Nepal'
    };
    var street1 = addresses.streetAddress1, streetAddress2 = addresses.streetAddress2;
    console.log(street1);
    console.log(streetAddress2);
}
function deepDestructuringObject() {
    var addresses = {
        streetAddress1: 'jhamshikhel',
        streetAddress2: 'green house',
        city: 'Lalitpur',
        state: 'Bagmati',
        country: 'Nepal'
    };
    var street1 = addresses.streetAddress1, streetAddress2 = addresses.streetAddress2;
    var employee = {
        workAddress: addresses,
        position: {
            department: {
                name: 'Development'
            }
        }
    };
    var city = employee.workAddress.city, departmentName = employee.position.department.name;
    console.log(city);
    console.log(departmentName);
    var categorizedEmployee = function (_a) {
        var _b = _a.workAddress.city, city = _b === void 0 ? '' : _b, _c = _a.position.department.name, departmentName = _c === void 0 ? '' : _c;
        return {
            city: city, departmentName: departmentName
        };
    };
    console.log(categorizedEmployee(employee));
}
function destructuringArray() {
    var names = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
    // const names: string[] = [];
    var firstTraditional = names[0];
    console.log(firstTraditional);
    // ... in here is the rest operator
    var _a = names || [], _b = _a[0], firstName = _b === void 0 ? 'Kripa' : _b, _c = _a[1], secondName = _c === void 0 ? 'Abhisekh' : _c, more = _a.slice(2);
    console.log(firstName);
    console.log(secondName);
    console.log(more);
}
function restOperator() {
    // const printName = function (...items: Array<string>) {
    //     items.forEach(value => console.log("Hello " + value + "."));
    // };
    var printName = function () {
        var items = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            items[_i] = arguments[_i];
        }
        return items.forEach(function (value) { return console.log("Hello " + value + "."); });
    };
    printName();
    printName('Ashim', 'Suraj', 'Shekhar');
    var names = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
    // ... in here is spread operator
    printName.apply(void 0, names);
}
function processTaggedTemplate() {
    var name = 'Ashim';
    var friend = function (strings) {
        var substitutions = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            substitutions[_i - 1] = arguments[_i];
        }
        if (!substitutions[0]) {
            substitutions[0] = 'Friend';
        }
        return taggedTemplate(strings, substitutions);
    };
    console.log(friend(templateObject_1 || (templateObject_1 = __makeTemplateObject(["Hello, ", "..."], ["Hello, ", "..."])), name));
}
function taggedTemplate(strings, substitutions) {
    var result = [];
    substitutions.forEach(function (sub, index) {
        result.push(strings[index], sub);
    });
    result.push(strings[strings.length - 1]);
    return result.join('');
}
function enhancedLoop() {
    var names = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
    for (var index in names) {
        console.log(names[index]);
    }
    for (var _i = 0, names_1 = names; _i < names_1.length; _i++) {
        var name_1 = names_1[_i];
        console.log(name_1);
    }
    var instrument = 'Guitar';
    for (var _a = 0, instrument_1 = instrument; _a < instrument_1.length; _a++) {
        var item = instrument_1[_a];
        console.log(item);
    }
}
var templateObject_1;

},{"./wowify":2}],2:[function(require,module,exports){
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function wowify() {
    var items = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        items[_i] = arguments[_i];
    }
    var result = [];
    items.forEach(function (value) { return result.push("wow... " + value); });
    return result;
}
exports.default = wowify;
exports.wowify = wowify;
function mehify() {
    var items = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        items[_i] = arguments[_i];
    }
    var result = [];
    items.forEach(function (value) { return result.push("meh... " + value); });
    return result;
}
exports.mehify = mehify;

},{}]},{},[1,2]);
