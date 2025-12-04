import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string): string[][] => {
  return readTxtFile(filepath)
    .split("\n")
    .map((line) => line.split(""));
};

const isRoll = (char: string): boolean => char === "@";

export const returnNeighbours = (
  matrix: string[][],
  position: number[]
): string[] => {
  const [row, col] = position;
  return [
    matrix[row][col - 1], //x y-1
    matrix[row][col + 1], //x y-1
    !matrix[row - 1] ? [][col] : matrix[row - 1][col], //nasty attempt to handle undefined indexing
    !matrix[row + 1] ? [][col] : matrix[row + 1][col],
    !matrix[row + 1] ? [][col - 1] : matrix[row + 1][col - 1],
    !matrix[row - 1] ? [][col + 1] : matrix[row - 1][col + 1],
    !matrix[row - 1] ? [][col - 1] : matrix[row - 1][col - 1],
    !matrix[row + 1] ? [][col + 1] : matrix[row + 1][col + 1],
  ].filter((neighbour) => !!neighbour);
};

const returnRollsWithXNeighbours = (
  input: string[][],
  maxNeighbours: number
) => {
  const validOnes: number[][] = [];

  input.forEach((row, x) => {
    row.forEach((val, y) => {
      if (isRoll(val)) {
        const neighbourRolls = returnNeighbours(input, [x, y]).filter(isRoll);
        if (neighbourRolls.length < maxNeighbours) {
          validOnes.push([x, y]);
        }
      }
    });
  });
  return validOnes;
};

export const getAnswerPart1 = (filepath: string): number => {
  const input = parseInput(filepath);
  const validOnes = returnRollsWithXNeighbours(input, 4);
  return validOnes.length;
};

export const getAnswerPart2 = (filepath: string): number => {
  let input = parseInput(filepath);
  let maxRolls = 0;

  let validOnes = returnRollsWithXNeighbours(input, 4);
  maxRolls += validOnes.length;

  while (validOnes.length > 0) {
    validOnes.forEach(([x, y]) => (input[x][y] = "x"));
    validOnes = returnRollsWithXNeighbours(input, 4);
    maxRolls += validOnes.length;
  }

  return maxRolls;
};
