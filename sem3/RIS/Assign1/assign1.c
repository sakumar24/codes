// Destination buffer.
#define BUF BASE_SZ

// Source buffers. Make each big enough that the size checks in the OK
// versions are necessary to ensure safety.
#define GECOS BASE_SZ + 2
#define LOGIN BASE_SZ + 2

#include "constants.h"

int main (void)
{
  // these were parameters
  char login[LOGIN + 1];
  char gecos[GECOS + 1];

  char buf[BUF + 1];
  char c;
  int i, j;
  int p, l;

  login[(int) (sizeof login - 1)] = EOS;
  gecos[(int) (sizeof gecos - 1)] = EOS;

  i = 0;
  if (gecos[i] == '*')
    i++;

  /* No check on whether we'd overflow buf[] */

  c = gecos[i];
  j = 0;
  while (c != EOS && c != ',' && c != ';' && c != '%')
  {
    if (c == '&')
    {
    
      (void) r_strcpy (buf + j, login);
    }
    else
    {
    
      buf[j] = c;
      j++;
    }	    
    i++;
    c = gecos[i];
  }
  buf[j] = EOS;
  return 0;
}
