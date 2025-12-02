import { readTxtFile } from "../../y2023/util/readTxtFile";

export const parseInput = (filepath: string) =>
  readTxtFile(filepath)
    .split(",")
    .map((range) => range.split("-").map((num) => parseInt(num)))
    .map(([start, end]) => createRange(start, end));

const createRange = (start: number, end: number) =>
  Array.from({ length: end - start + 1 }, (_, index) => start + index);

export const getInvalidIdsPart1 = (range: number[]) =>
  range.filter((num) => {
    const stringNum = num.toString();

    if (stringNum.length % 2 !== 0) {
      //To be invalid you must repeat a set of digits twice, therefore must have an even number of digits!
      return false;
    }

    const left = stringNum.substring(0, stringNum.length / 2);
    const right = stringNum.substring(stringNum.length / 2, stringNum.length);

    return (
      left === right &&
      !(left.toString().startsWith("0") && right.toString().startsWith("0"))
    );
  });

export const getInvalidIdsPart2 = (range: number[]) =>
  range.filter((num) => {
    const stringNum = num.toString();

    let endIndex = 1;

    while (endIndex <= stringNum.length - 1) {
      const currentMatch = stringNum.substring(0, endIndex);
      const numSplitOnSubstring = stringNum.split(currentMatch);

      if (
        numSplitOnSubstring.every((seq) => seq === "") &&
        numSplitOnSubstring.length > 2
      ) {
        //Super hacky but if we've found our repeating sequence we can neatly split
        // the string on it and be left with nothing but empty strings!
        return true;
      }

      endIndex++;
    }

    return false;
  });

export const getAnswer = (
  filepath: string,
  invalidIdFunc: (range: number[]) => number[],
) =>
  parseInput(filepath)
    .flatMap(invalidIdFunc)
    .reduce((sum, current) => (sum += current), 0);
