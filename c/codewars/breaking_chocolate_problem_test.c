#include <criterion/criterion.h>
#include <stdint.h>

uint32_t breaking_chocolate(uint32_t n, uint32_t m);

Test(breaking_chocolate, sample_test) 
{
  cr_assert_eq(breaking_chocolate(5, 5), 24);
  cr_assert_eq(breaking_chocolate(1, 1), 0);
}
