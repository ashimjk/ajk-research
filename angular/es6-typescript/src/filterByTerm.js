"use strict";
/////////////////////////////////////////////////////
function filterByTerm(input, searchTerm) {
    if (!searchTerm)
        throw Error("searchTerm cannot be empty");
    if (!input.length)
        throw Error("input cannot be empty");
    const regex = new RegExp(searchTerm, "i");
    return input.filter(function (arrayElement) {
        return arrayElement.url.match(regex);
    });
}
filterByTerm([{ url: "input string" }], "java");
/////////////////////////////////////////////////////
function favroiteFood(name) {
    let fav;
    if (name === 'ashim') {
        let fav = 'momo';
    }
    else {
        let fav = 'chowmein';
    }
    return fav;
}
