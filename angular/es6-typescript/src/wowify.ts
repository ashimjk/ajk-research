function wowify(...items: string[]) {
    const result: string[] = [];
    items.forEach(value => result.push(`wow... ${value}`));
    return result;
}

function mehify(...items: string[]) {
    const result: string[] = [];
    items.forEach(value => result.push(`meh... ${value}`));
    return result;
}

export {wowify, mehify};