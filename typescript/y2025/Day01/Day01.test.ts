import { createLinkedLoop, getPassword, getPasswordPart2 } from "./Day01";

describe("Day01 tests", () => {
  it("linked loop returns a circular loop of items", () => {
    const loopOf5 = createLinkedLoop(5);

    expect(loopOf5.length).toBe(5);

    const start = loopOf5[0];
    const end = loopOf5[4];
    const third = loopOf5[2];

    expect(start).toMatchObject({
      current: 0,
    });
    expect(start.forward()).toEqual(1);
    expect(start.backward()).toEqual(4);

    expect(end).toMatchObject({
      current: 4,
    });
    expect(end.forward()).toEqual(0);
    expect(end.backward()).toEqual(3);

    expect(third).toMatchObject({
      current: 2,
    });

    expect(third.forward()).toEqual(3);
    expect(third.backward()).toEqual(1);
  });

  it("passes the test input", () => {
    expect(getPassword("y2025/Day01/test-input.txt")).toBe(3);
  });

  it("passes the real input", () => {
    expect(getPassword("y2025/Day01/input.txt")).toBe(1141);
  });

  it("passes the test input part 2", () => {
    expect(getPasswordPart2("y2025/Day01/test-input.txt")).toBe(6);
  });

  it("passes the real input part 2", () => {
    expect(getPasswordPart2("y2025/Day01/input.txt")).toBe(6);
  });
});
