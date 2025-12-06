import { getAnswerPart1, getAnswerPart2, parseInput } from "./Day05";

describe("Day05", () => {
    it("parses input as expected", () => {
        expect(parseInput("y2025/Day05/test-input.txt")).toStrictEqual([
            [
                [3, 5],
                [10, 14],
                [16, 20],
                [12, 18],
            ],
            [1, 5, 8, 11, 17, 32],
        ]);
    });

    it("passes the test data p1", () => {
        expect(getAnswerPart1("y2025/Day05/test-input.txt")).toStrictEqual(3);
    });

    it("passes the real data p1", () => {
        expect(getAnswerPart1("y2025/Day05/input.txt")).toStrictEqual(735);
    });


    it("passes the test data p2", () => {
        expect(getAnswerPart2("y2025/Day05/test-input.txt")).toStrictEqual(14);
    });

     it("passes the test data p2", () => {
        expect(getAnswerPart2("y2025/Day05/input.txt")).toStrictEqual(344306344403172);
    });
    
})
