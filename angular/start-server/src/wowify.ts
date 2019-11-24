export function wowify(...names: string[]) {
    const results: string[] = [];

    names.forEach(value => {
        results.push(`wow... ${value}`);
    });

    return results;
}