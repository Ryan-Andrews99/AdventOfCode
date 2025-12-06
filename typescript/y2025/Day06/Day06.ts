import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string) =>
  readTxtFile(filepath)
    .split("\n")
    .map((line) => line.trim())
    .map((line) =>
      line
        .split(" ")
        .map((c) => c.trim())
        .filter((c) => c !== ""),
    );

export const flipGrid = (grid: string[][]) => {
  const newGrid: string[][] = new Array(grid[0].length).fill(0).map((_) => []);

  grid.forEach((row, x) =>
    row.forEach((val, y) => {
      newGrid[y][x] = val;
    }),
  );
  return newGrid;
};

const applyOperationToRow = (row: string[]) => {
  const operation = row.pop();
  const isMultiplcation = operation === "*";
  return row
    .map((i) => parseInt(i))
    .reduce(
      (s, n) => {
        isMultiplcation ? (s *= n) : (s += n);
        return s;
      },
      isMultiplcation ? 1 : 0,
    );
};

export const getAnswerPart1 = (filepath: string) =>
  flipGrid(parseInput(filepath))
    .map(applyOperationToRow)
    .reduce((s, i) => (s += i), 0);

export const getAnswerPart2 = (filepath: string) =>
  flipGrid(
    readTxtFile(filepath)
      .split("\n")
      .map((line) => line.split("")),
  )
    .reverse()
    .map((line) => line.filter((item) => item !== " "))
    .reduce(
      (acc, item) => {
        //split on empty arrays as these indicate new operations
        if (item.length === 0) {
          acc.push([]);
        } else {
          acc[acc.length - 1].push(item);
        }
        return acc;
      },
      [[]] as string[][][],
    )
    .map((l) => l.map((item) => item.join("")))
    .map((r) =>
      r.flatMap((item) => {
        if (item.includes("+")) {
          return [item.replace("+", ""), "+"];
        } else if (item.includes("*")) {
          return [item.replace("*", ""), "*"];
        }
        return item;
      }),
    )
    .map(applyOperationToRow)
    .reduce((s, i) => (s += i), 0);
