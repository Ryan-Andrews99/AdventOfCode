import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string): [number[][], number[]] => {
  const [range, ingredients] = readTxtFile(filepath).split("\n\n");
  return [
    range.split("\n").map((range) => range.split("-").map((n) => parseInt(n))),
    ingredients.split("\n").map((n) => parseInt(n)),
  ];
};

export const getAnswerPart1 = (filepath: string) => {
  const [ranges, ingredients] = parseInput(filepath);
  return ingredients.filter((i) =>
    ranges.some(([start, end]) => i <= end && i >= start),
  ).length;
};

export const isRangeOverlap = (
  [start1, end1]: number[],
  [start2, end2]: number[],
): boolean => end1 >= start2 && end2 >= start1;

export const mergeRanges = (
  [start1, end1]: number[],
  [start2, end2]: number[],
): number[] => [Math.min(start1, start2), Math.max(end1, end2)];

const mergeAllRanges = (ranges: number[][], currentIndex = 0): number[][] => {
  if (currentIndex > ranges.length - 2) {
    return ranges;
  }

  const current = ranges[currentIndex];
  const next = ranges[currentIndex + 1];

  if (!isRangeOverlap(current, next)) {
    return mergeAllRanges(ranges, currentIndex + 1);
  }

  ranges.splice(currentIndex, 1);
  ranges[currentIndex] = mergeRanges(current, next);
  return mergeAllRanges(ranges, currentIndex);
};

export const getAnswerPart2 = (filepath: string) => {
  const [ranges] = parseInput(filepath);
  const sortedRanges = ranges.sort(([start1], [start2]) =>
    start1 > start2 ? 1 : -1,
  );
  return mergeAllRanges(sortedRanges)
    .map(([start, end]) => end - start + 1)
    .reduce((s, i) => (s += i));
};
