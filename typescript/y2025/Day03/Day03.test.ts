import { getAnswerPart1, getAnswerPart2, getNHighestFromBattery, parseInput } from "./Day03";

describe("Day03 tests", () => {
    it("parse the input as expected", () => {
        expect(parseInput("y2025/Day03/test-input.txt")).toStrictEqual([
            [9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1],
            [8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9],
            [2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8],
            [8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1],
        ]);
    });

    it("returns the top 2 numbers in order", () => {
        expect(
            getNHighestFromBattery([9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1], 2),
        ).toStrictEqual([9, 8]);
        expect(
            getNHighestFromBattery([8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9], 2),
        ).toStrictEqual([8, 9]);
        expect(
            getNHighestFromBattery([2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8], 2),
        ).toStrictEqual([7, 8]);
        expect(
            getNHighestFromBattery([8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1], 2),
        ).toStrictEqual([9, 2]);
    });

    it("passes the test input p1", () => {
        expect(getAnswerPart1("y2025/Day03/test-input.txt")).toStrictEqual(357);
    });

    it("passes the real input p1", () => {
        expect(getAnswerPart1("y2025/Day03/input.txt")).toStrictEqual(17376);
    });

    it("returns the top 12 numbers in order", () => {
        expect(
            getNHighestFromBattery([9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1], 12),
        ).toStrictEqual([9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1]);
        expect(
            getNHighestFromBattery([8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9], 12),
        ).toStrictEqual([8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9]);
        expect(
            getNHighestFromBattery([2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8], 12),
        ).toStrictEqual([4, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8]);
        expect(
            getNHighestFromBattery([8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1], 12),
        ).toStrictEqual([8, 8, 8, 9, 1, 1, 1, 1, 2, 1, 1, 1]);
    });

    it("passes the test input p2", () => {
        expect(getAnswerPart2("y2025/Day03/test-input.txt")).toStrictEqual(3121910778619);
    });

     it("passes the real input p2", () => {
        expect(getAnswerPart2("y2025/Day03/input.txt")).toStrictEqual(172119830406258);
    });
});
