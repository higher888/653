all: 1 2 3 4

1:
	gcc -std=c99 -O0 -g sll_buggy.c -osll_buggy

2:
	valgrind --leak-check=yes ./sll_buggy

3:
	gcc -std=c99 -O0 -g sll_fixed.c -osll_fix

4:
	valgrind --leak-check=yes ./sll_fix


clean:
	rm -f sll_buggy sll_fix

	
