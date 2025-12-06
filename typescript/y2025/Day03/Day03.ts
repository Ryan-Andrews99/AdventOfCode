import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string) =>
    readTxtFile(filepath)
        .split("\n")
        .map((line) => line.split("").map((i) => parseInt(i)));

export const getNHighestFromBattery = (battery: number[], max: number) => {
    const highest: number[] = [];
    let currentIndex = 0;

    while (highest.length < max) {
        const current = battery[currentIndex];

        if (
            !battery
                .slice(currentIndex)
                .some(
                    (n, index, remaining) =>
                        n > current && remaining.length - index >= max - highest.length,
                )
        ) {
            highest.push(current);
            battery.splice(currentIndex, 1);
            continue

        }

        currentIndex++;
    }
    return highest;
};

export const getAnswerPart1 = (filepath: string) =>
    parseInput(filepath)
        .map((b) => getNHighestFromBattery(b, 2))
        .flatMap(num => parseInt(num.join("")))
        .reduce((sum, i) => (sum += i));

export const getAnswerPart2 = (filepath: string) =>
    parseInput(filepath)
        .map((b) => getNHighestFromBattery(b, 12))
        .flatMap(num => parseInt(num.join("")))
        .reduce((sum, i) => (sum += i));
