// access the wowify as a module
// import * as wowify from './wowify';

// access exported function only
import {wowify} from './wowify';

// destructuringObject();
// deepDestructuringObject();
// destructuringArray();
// restOperator();
// processTaggedTemplate();
// enhancedLoop();

const names: string[] = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
console.log(wowify(...names));

function destructuringObject() {
    const addresses = {
        streetAddress1: 'jhamshikhel',
        streetAddress2: 'green house',
        city: 'Lalitpur',
        state: 'Bagmati',
        country: 'Nepal'
    };

    const {streetAddress1: street1, streetAddress2} = addresses;

    console.log(street1);
    console.log(streetAddress2);
}

function deepDestructuringObject() {
    const addresses = {
        streetAddress1: 'jhamshikhel',
        streetAddress2: 'green house',
        city: 'Lalitpur',
        state: 'Bagmati',
        country: 'Nepal'
    };

    const {streetAddress1: street1, streetAddress2} = addresses;

    const employee = {
        workAddress: addresses,
        position: {
            department: {
                name: 'Development'
            }
        }
    };

    const {workAddress: {city}, position: {department: {name: departmentName}}} = employee;

    console.log(city);
    console.log(departmentName);

    const categorizedEmployee = function ({workAddress: {city = ''}, position: {department: {name: departmentName = ''}}}) {
        return {
            city: city, departmentName: departmentName
        }
    };

    console.log(categorizedEmployee(employee));
}

function destructuringArray() {
    const names: string[] = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];
    // const names: string[] = [];

    const firstTraditional = names[0];
    console.log(firstTraditional);

    // ... in here is the rest operator
    const [firstName = 'Kripa', secondName = 'Abhisekh', ...more] = names || [];

    console.log(firstName);
    console.log(secondName);
    console.log(more);
}

function restOperator() {
    // const printName = function (...items: Array<string>) {
    //     items.forEach(value => console.log("Hello " + value + "."));
    // };

    const printName = (...items: Array<string>) => items.forEach(value => console.log(`Hello ${value}.`));

    printName();
    printName('Ashim', 'Suraj', 'Shekhar');

    const names: string[] = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];

    // ... in here is spread operator
    printName(...names);
}

function processTaggedTemplate() {
    const name = 'Ashim';

    const friend = function (strings: TemplateStringsArray, ...substitutions: string[]) {
        if (!substitutions[0]) {
            substitutions[0] = 'Friend';
        }
        return taggedTemplate(strings, substitutions);
    };

    console.log(friend`Hello, ${name}...`);
}

function taggedTemplate(strings: TemplateStringsArray, substitutions: string[]) {
    const result = [];

    substitutions.forEach((sub, index) => {
        result.push(strings[index], sub);
    });

    result.push(strings[strings.length - 1]);
    return result.join('');
}

function enhancedLoop() {
    const names: string[] = ['Ashim', 'Suraj', 'Shekhar', 'Bhuwan', 'Ganesh'];

    for (let index in names) {
        console.log(names[index]);
    }

    for (let name of names) {
        console.log(name);
    }

    const instrument = 'Guitar';
    for (let item of instrument) {
        console.log(item);
    }
}
