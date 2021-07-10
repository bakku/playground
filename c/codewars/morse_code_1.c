#include <stdlib.h>
#include <string.h>
#include <stdio.h>

const char *morse[55] = {
    ".-",     "-...",   "-.-.",   "-..",     ".",      "..-.",     "--.",
    "....",   "..",     ".---",   "-.-",     ".-..",   "--",       "-.",
    "---",    ".--.",   "--.-",   ".-.",     "...",    "-",        "..-",
    "...-",   ".--",    "-..-",   "-.--",    "--..",   "-----",    ".----",
    "..---",  "...--",  "....-",  ".....",   "-....",  "--...",    "---..",
    "----.",  ".-.-.-", "--..--", "..--..",  ".----.", "-.-.--",   "-..-.",
    "-.--.",  "-.--.-", ".-...",  "---...",  "-.-.-.", "-...-",    ".-.-.",
    "-....-", "..--.-", ".-..-.", "...-..-", ".--.-.", "...---..."};

const char *ascii[55] = {
    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  "K", "L", "M",  "N",
    "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",  "Y", "Z", "0",  "1",
    "2", "3", "4", "5", "6", "7", "8", "9", ".", ",",  "?", "'", "!",  "/",
    "(", ")", "&", ":", ";", "=", "+", "-", "_", "\"", "$", "@", "SOS"};

const char *decode_single_morse(const char* code) {
  for (int i = 0; i < 55; i++) {
    if (strcmp(code, morse[i]) == 0) {
      return ascii[i];
    }
  }

  return NULL;
}

const char *next_char(char** morse_code) {
  // detect whitespace
  if (**morse_code == ' ' && *(*morse_code + 1) == ' ' && *(*morse_code + 2) == ' ') {
    *morse_code = *morse_code + 3;
    return " ";
  }

  // skip character separator
  if (**morse_code == ' ') *morse_code = *morse_code + 1;

  char code[10] = { 0 };

  int i = 0;

  while (**morse_code != ' ' && **morse_code != '\0') {
    code[i] = **morse_code;
    code[i+1] = '\0';
    *morse_code = *morse_code + 1;
    i++;
  }

  return decode_single_morse(code);
}

char *str_dup(const char* str) {
  char* ret = malloc(strlen(str) + 1);

  if (!ret) {
    fprintf(stderr, "out of memory\n");
    exit(EXIT_FAILURE);
  }

  return strcpy(ret, str);
}

char *str_append(const char *first, const char *second) {
  char *ret = malloc(strlen(first) + strlen(second) + 1);
  strcpy(ret, first);
  strcat(ret, second);
  return ret;
}

char *str_trim(const char *str) {
  int start = 0, end = (strlen(str) - 1);
  char *str_copy = str_dup(str);
  char *search, *result;

  search = str_copy;

  while (*search == ' ') {
    search++;
    start++;
  }

  search = str_copy + end;

  while (*search == ' ') {
    search--;
    end--;
  }

  result = malloc(end - start + 2);

  for (int i = start, j = 0; i <= end; i++, j++) {
    result[j] = str_copy[i];
  }

  result[end - start + 1] = '\0';

  return result;
}

char *decode_morse(const char* morse_code) {
  char *prevResult = "", *result = "";
  char *morse_code_cpy = str_trim(morse_code);

  while (*morse_code_cpy != '\0') {
    prevResult = result;
    const char *translation = next_char(&morse_code_cpy);
    result = str_append(prevResult, translation);

    if (!prevResult) free(prevResult);
  }

  return result;
}
