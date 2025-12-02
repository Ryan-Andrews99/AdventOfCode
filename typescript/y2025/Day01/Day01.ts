import { readTxtFile } from "../../y2023/util/readTxtFile";

type LinkedItem = {
  current: number;
  forward: () => number;
  backward: () => number;
};

export const getPasswordPart2 = (filePath: string) => {
  const loop = createLinkedLoop(100);
  console.log(loop);
  let current = loop[50];
  let clicksThroughZero = 0;

  const parsedInput = parseInput(filePath);

  parsedInput.forEach((step) => {
    const numStep = parseStep(step);
    if (numStep > 0) {
      for (let i = 0; i < numStep; i++) {
        current = loop[current.backward()];

        if (current.current === 0) {
          clicksThroughZero++;
        }
      }
    } else {
      for (let i = 0; i > numStep; i--) {
        current = loop[current.forward()];

        if (current.current === 0) {
          clicksThroughZero++;
        }
      }
    }
  });
  return clicksThroughZero;
};

export const getPassword = (filePath: string) => {
  const loop = createLinkedLoop(100);
  console.log(loop);
  let current = loop[50];
  let password = 0;

  const parsedInput = parseInput(filePath);

  parsedInput.forEach((step) => {
    const numStep = parseStep(step);
    if (numStep > 0) {
      for (let i = 0; i < numStep; i++) {
        current = loop[current.backward()];
      }
    } else {
      for (let i = 0; i > numStep; i--) {
        current = loop[current.forward()];
      }
    }

    if (current.current === 0) {
      password++;
    }
  });
  return password;
};

const parseInput = (filePath: string) => readTxtFile(filePath).split("\n");

const isForward = (step: string) => step.startsWith("L");

const parseStep = (step: string) =>
  isForward(step)
    ? parseInt(step.substring(1, step.length))
    : -parseInt(step.substring(1, step.length));

export const createLinkedLoop = (length: number): LinkedItem[] => {
  return Array.from(Array(length).keys()).map((_, index) => {
    if (index === 0) {
      return {
        current: index,
        forward: () => index + 1,
        backward: () => length - 1,
      };
    } else if (index === length - 1) {
      return {
        current: index,
        forward: () => 0,
        backward: () => index - 1,
      };
    } else {
      return {
        current: index,
        forward: () => index + 1,
        backward: () => index - 1,
      };
    }
  });
};
