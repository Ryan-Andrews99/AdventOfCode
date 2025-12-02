import {
  getAnswer,
  getInvalidIdsPart1,
  getInvalidIdsPart2,
  parseInput,
} from "./Day02";

describe("Day02 tests", () => {
  it("parse the test input in the expected way", () => {
    expect(parseInput("y2025/Day02/test-input.txt")).toStrictEqual([
      [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22],
      [
        95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109,
        110, 111, 112, 113, 114, 115,
      ],
      [
        998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009,
        1010, 1011, 1012,
      ],
      [
        1188511880, 1188511881, 1188511882, 1188511883, 1188511884, 1188511885,
        1188511886, 1188511887, 1188511888, 1188511889, 1188511890,
      ],
      [222220, 222221, 222222, 222223, 222224],
      [1698522, 1698523, 1698524, 1698525, 1698526, 1698527, 1698528],
      [446443, 446444, 446445, 446446, 446447, 446448, 446449],
      [38593856, 38593857, 38593858, 38593859, 38593860, 38593861, 38593862],
      [565653, 565654, 565655, 565656, 565657, 565658, 565659],
      [
        824824821, 824824822, 824824823, 824824824, 824824825, 824824826,
        824824827,
      ],
      [
        2121212118, 2121212119, 2121212120, 2121212121, 2121212122, 2121212123,
        2121212124,
      ],
    ]);
  });

  it("gets the correct invalid IDs in the test input", () => {
    expect(
      parseInput("y2025/Day02/test-input.txt").map(getInvalidIdsPart1),
    ).toStrictEqual([
      [11, 22],
      [99],
      [1010],
      [1188511885],
      [222222],
      [],
      [446446],
      [38593859],
      [],
      [],
      [],
    ]);
  });

  it("passes the test input part 1", () => {
    expect(
      getAnswer("y2025/Day02/test-input.txt", getInvalidIdsPart1),
    ).toStrictEqual(1227775554);
  });

  it("passes the real input part 1", () => {
    expect(
      getAnswer("y2025/Day02/input.txt", getInvalidIdsPart1),
    ).toStrictEqual(19574776074);
  });

  it("gets the correct invalid IDs in the test input", () => {
    expect(
      parseInput("y2025/Day02/test-input.txt").map(getInvalidIdsPart2),
    ).toStrictEqual([
      [11, 22],
      [99, 111],
      [999, 1010],
      [1188511885],
      [222222],
      [],
      [446446],
      [38593859],
      [565656],
      [824824824],
      [2121212121],
    ]);
  });

  it("passes the real input part 2", () => {
    expect(
      getAnswer("y2025/Day02/input.txt", getInvalidIdsPart2),
    ).toStrictEqual(25912654282);
  });
});
