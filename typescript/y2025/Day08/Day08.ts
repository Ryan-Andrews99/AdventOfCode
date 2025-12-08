import { readTxtFile } from "../../y2023/util/readTxtFile";

type ThreeDimensionalCoords = [number, number, number];

export const parseInput = (filepath: string): ThreeDimensionalCoords[] =>
  readTxtFile(filepath)
    .split("\n")
    .map(
      (line) =>
        line.split(",").map((i) => parseInt(i)) as ThreeDimensionalCoords
    );

export const findSmallestDistancePair = (
  points: ThreeDimensionalCoords[]
): ThreeDimensionalCoords[] => {
  if (points.length <= 4) {
    return returnSmallestDistancePair(points);
  }

  const midPoint = Math.floor(points.length / 2);
  const left = points.slice(0, midPoint - 1);
  const right = points.slice(midPoint + 1, points.length);

  const smallestLeft = findSmallestDistancePair(left);
  const smallestRight = findSmallestDistancePair(right);

  const smallestTotal = findSmallestDistancePair([
    ...smallestLeft,
    ...smallestRight,
  ]);

  return smallestTotal;
};

const returnSmallestDistancePair = (
  points: ThreeDimensionalCoords[]
): ThreeDimensionalCoords[] => {
  let minLength = Number.POSITIVE_INFINITY;
  let mins: ThreeDimensionalCoords[] = [];
  let currentIndex = 0;

  while (currentIndex < points.length - 1) {
    const current = points[currentIndex];
    const remaining = points.toSpliced(currentIndex);
    remaining.forEach((coord) => {
      if (isSame(coord, current)) {
        return;
      }

      const distanceToCurrent = calculateDistance(coord, current);
      if (distanceToCurrent < minLength) {
        minLength = distanceToCurrent;
        mins = [coord, current];
      }
    });
    currentIndex++;
  }

  return mins;
};

const calculateDistance = (
  [x1, y1, z1]: ThreeDimensionalCoords,
  [x2, y2, z2]: ThreeDimensionalCoords
) => Math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2 + (z2 + z1 ** 2));

const isSame = (
  [x1, y1, z1]: ThreeDimensionalCoords,
  [x2, y2, z2]: ThreeDimensionalCoords
) => x2 === x1 && y2 === y1 && z2 === z1;
