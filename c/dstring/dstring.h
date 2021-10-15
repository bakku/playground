/* A simple dynamic string implementation */

#ifndef dstring_h_
#define dstring_h_

#define DSTRING_ERROR_CODE int
#define DSTRING_SUCCESS 0
#define DSTRING_FAILURE 1

typedef struct {
    unsigned long length;
    char *str;
} String;

/*
  Creates a new dynamic string with 'value' as its
  initial value. A dynamic string must be free'd again
  via 'free_string' after it is not needed anymore.

  Returns either the newly created String as a pointer
  or NULL if the dynamic string could not be created.
*/
String* new_string(const char* value);

/*
  Appends 'value' to a dynamic string.

  Returns either DSTRING_SUCCESS or DSTRING_FAILURE.
*/
DSTRING_ERROR_CODE string_append(String* s, const char* value);

/*
  Frees a dynamic string.
*/
void free_string(String* s);

#endif // dstring_h_
