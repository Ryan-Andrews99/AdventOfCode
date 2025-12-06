import { flipGrid, getAnswerPart1, getAnswerPart2, parseInput } from "./Day06";

describe("Day06 tests", () => {
  it("parses the input correctly", () => {
    expect(parseInput("y2025/Day06/test-input.txt")).toStrictEqual([
      ["123", "328", "51", "64"],
      ["45", "64", "387", "23"],
      ["6", "98", "215", "314"],
      ["*", "+", "*", "+"],
    ]);
  });

  it("flips the grid", () => {
    expect(
      flipGrid([
        ["123", "328", "51", "64"],
        ["45", "64", "387", "23"],
        ["6", "98", "215", "314"],
        ["*", "+", "*", "+"],
      ]),
    ).toStrictEqual([
      ["123", "45", "6", "*"],
      ["328", "64", "98", "+"],
      ["51", "387", "215", "*"],
      ["64", "23", "314", "+"],
    ]);
  });

  it("passes the test data", () => {
    expect(getAnswerPart1("y2025/Day06/test-input.txt")).toStrictEqual(4277556);
  });

  it("passes the real data", () => {
    expect(getAnswerPart1("y2025/Day06/input.txt")).toStrictEqual(
      4951502530386,
    );
  });

  it("test input part 2", () => {
    expect(getAnswerPart2("y2025/Day06/test-input.txt")).toStrictEqual(3263827);
  });

  it("real input part 2", () => {
    expect(getAnswerPart2("y2025/Day06/input.txt")).toStrictEqual(
      8486156119946,
    );
  });
});
