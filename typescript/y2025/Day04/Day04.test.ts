import { getAnswerPart1, getAnswerPart2, parseInput } from "./Day04";

describe("Day04 tests", () => {
  it("parses the input as expected", () => {
    expect(parseInput("y2025/Day04/test-input.txt")).toStrictEqual([
      [".", ".", "@", "@", ".", "@", "@", "@", "@", "."],
      ["@", "@", "@", ".", "@", ".", "@", ".", "@", "@"],
      ["@", "@", "@", "@", "@", ".", "@", ".", "@", "@"],
      ["@", ".", "@", "@", "@", "@", ".", ".", "@", "."],
      ["@", "@", ".", "@", "@", "@", "@", ".", "@", "@"],
      [".", "@", "@", "@", "@", "@", "@", "@", ".", "@"],
      [".", "@", ".", "@", ".", "@", ".", "@", "@", "@"],
      ["@", ".", "@", "@", "@", ".", "@", "@", "@", "@"],
      [".", "@", "@", "@", "@", "@", "@", "@", "@", "."],
      ["@", ".", "@", ".", "@", "@", "@", ".", "@", "."],
    ]);
  });

  it("passes the test data part 1", () => {
    expect(getAnswerPart1("y2025/Day04/test-input.txt")).toStrictEqual(13);
  });

  it("passes the real data part 1", () => {
    expect(getAnswerPart1("y2025/Day04/input.txt")).toStrictEqual(1604);
  });

  it("passes the test data part 2", () => {
    expect(getAnswerPart2("y2025/Day04/test-input.txt")).toStrictEqual(43);
  });

  it("passes the real data part 2", () => {
    expect(getAnswerPart2("y2025/Day04/input.txt")).toStrictEqual(9397);
  });
});
