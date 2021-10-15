#include "dstring.h"

#include <stddef.h>
#include <stdlib.h>
#include <string.h>

String* new_string(const char* value) {
    String* s = (String*) malloc(sizeof(String));
    if (s == NULL) {
        return NULL;
    }

    s->str = (char*) malloc(strlen(value) + 1);
    strcpy(s->str, value);

    s->length = strlen(value);

    return s;
}

DSTRING_ERROR_CODE string_append(String* s, const char* value) {
    unsigned long value_len = strlen(value);

    s->str = realloc(s->str, s->length + value_len + 1);
    if (s->str == NULL) {
        return DSTRING_FAILURE;
    }

    s->str = strcat(s->str, value);
    s->length = s->length + value_len;

    return DSTRING_SUCCESS;
}

void free_string(String* s) {
    free(s->str);
    free(s);
}
