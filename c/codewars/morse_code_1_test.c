#include <criterion/criterion.h>
#include <stdio.h>
#include <string.h>

char *decode_morse(const char *morse_code);

Test(Example_Tests, should_pass_all_the_tests_provided) {
  cr_assert_eq(strcmp(decode_morse(".... . -.--   .--- ..- -.. ."), "HEY JUDE"), 0);
  cr_assert_eq(strcmp(decode_morse("   .... . -.--   "), "HEY"), 0);

  // random tests
  cr_assert_eq(
    strcmp(decode_morse(".-.. .- -.... --. .----. ...---...   .----.   ---..   "
                        "-..-   ...-..- --..-- .--.-. ---.. -... ---.. -.. .-- .--. "
                        "..--- .. .---- .-..-. ..--..   -. ..--.- . ----. ----. ...-- "
                        ".-.. ..--.. -..-.   ..-. ..-. ...-..-   - ....   -.- ---...   -- .."),
           "LA6G'SOS ' 8 X $,@8B8DWP2I1\"? N_E993L?/ FF$ TH K: MI"), 0);

  cr_assert_eq(
    strcmp(decode_morse(".- --... -....   .-.-.- --... -..-   -..   ....- ....- ..-. -----"),
           "A76 .7X D 44F0"), 0);

  cr_assert_eq(
    strcmp(decode_morse(".-.-.- ..---   -.. -.--.-   -..-. --...   .-   -.--. ...-..- ..--..   --..--"),
           ".2 D) /7 A ($? ,"), 0);

  cr_assert_eq(
    strcmp(decode_morse("-.-. .... .-.   -..   ...---...   ----- -..- .. -. ..... .-.. -..   "
                        "---..   .-. -..   .- ..--.- ... .-.. -.--   --   ...---... ...- - "
                        "--..-- -.--.-   .---- ---... ..--..   .-..-. ...."),
           "CHR D SOS 0XIN5LD 8 RD A_SLY M SOSVT,) 1:? \"H"), 0);

  cr_assert_eq(
    strcmp(decode_morse("..-. -...-   --.   -.-.-. ..---   ---...   ...- - ...---... .--.-. ...- .--   ..---"),
           "F= G ;2 : VTSOS@VW 2"), 0);
}
