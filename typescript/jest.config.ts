import { JestConfigWithTsJest } from "ts-jest";

const config: JestConfigWithTsJest = {
  preset: "ts-jest",
  coveragePathIgnorePatterns: ["<rootDir>/node_modules/*"],
  testMatch: ["<rootDir>/y202*/**/*.test.ts"],
};

export default config;
