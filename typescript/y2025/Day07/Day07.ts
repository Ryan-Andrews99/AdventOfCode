import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string) =>
  readTxtFile(filepath)
    .split("\n")
    .map((line) => line.split(""));

//Assumption: start is always in the first line
const findStart = (input: string[][]) => [0, input[0].indexOf("S")];

const walkGrid = (
  input: string[][],
  beamPositions: number[][],
  splits = 0,
): number => {
  if (beamPositions.every((beam) => beam[0] > input.length - 2)) {
    return splits;
  }
  const newBeamPositions = beamPositions.flatMap(([y, x]) => {
    const forwardStep = input[y + 1][x];

    if (forwardStep !== "^") {
      input[y][x] = "|";
      input[y + 1][x] = "|";
      return [[y + 1, x]];
    }

    splits += 1;
    input[y + 1][x - 1] = "|";
    input[y + 1][x + 1] = "|";
    return [
      [y + 1, x - 1],
      [y + 1, x + 1],
    ];
  });

  return walkGrid(
    input,
    //Hacky dedupe for array of arrays
    [...new Set(newBeamPositions.map((i) => JSON.stringify(i)))].map((i) =>
      JSON.parse(i),
    ),
    splits,
  );
};

export const getAnswerPart1 = (filepath: string) => {
  const input = parseInput(filepath);
  const [startY, startX] = findStart(input);
  return walkGrid(input, [[startY, startX]], 0);
};

//dfs
const getNumberOfPathsToEnd = (
  input: string[][],
  [y, x]: number[],
  cache: Map<string, number> = new Map(),
): number => {
  const key = `${y},${x}`;
  if (cache.has(key)) {
    return cache.get(key)!;
  }

  if (y >= input.length - 1) {
    return 1;
  }

  const next = input[y + 1][x];

  let paths = 0;
  if (next !== "^") {
    paths = getNumberOfPathsToEnd(input, [y + 1, x], cache);
  } else {
    paths += getNumberOfPathsToEnd(input, [y + 1, x + 1], cache);
    paths += getNumberOfPathsToEnd(input, [y + 1, x - 1], cache);
  }
  cache.set(key, paths);
  return paths;
};

export const getAnswerPart2 = (filepath: string) => {
  const input = parseInput(filepath);
  const [startY, startX] = findStart(input);
  return getNumberOfPathsToEnd(input, [startY, startX]);
};
