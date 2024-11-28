import { JestConfigWithTsJest } from "ts-jest";

const config: JestConfigWithTsJest = {
  preset: "ts-jest",
  coveragePathIgnorePatterns: ["<rootDir>/node_modules/*"],
  testMatch: ["<rootDir>/y2023/**/*.test.ts"],
};

export default config;
