package paralleltesting_test

import (
	"testing"

	paralleltesting "bakku.dev/playground/parallel-testing"
)

func Test_Add(t *testing.T) {
	t.Parallel()

	t.Log("running Test_Add")

	type test struct {
		a        int
		b        int
		expected int
	}

	tests := []test{
		{1, 2, 3},
		{2, 3, 5},
		{7, 5, 12},
	}

	for _, tc := range tests {
		actual := paralleltesting.Add(tc.a, tc.b)

		if actual != tc.expected {
			t.Fatalf("Expected %d, got %d\n", tc.expected, actual)
		}
	}
}

func Test_Mult(t *testing.T) {
	t.Parallel()

	t.Log("running Test_Mult")

	type test struct {
		a        int
		b        int
		expected int
	}

	tests := []test{
		{1, 2, 2},
		{2, 3, 6},
		{7, 5, 35},
	}

	for _, tc := range tests {
		actual := paralleltesting.Mult(tc.a, tc.b)

		if actual != tc.expected {
			t.Fatalf("Expected %d, got %d\n", tc.expected, actual)
		}
	}
}

func Test_Div(t *testing.T) {
	t.Parallel()

	t.Log("running Test_Div")

	type test struct {
		a        int
		b        int
		expected int
	}

	tests := []test{
		{1, 2, 0},
		{2, 3, 0},
		{7, 5, 1},
	}

	for _, tc := range tests {
		actual := paralleltesting.Div(tc.a, tc.b)

		if actual != tc.expected {
			t.Fatalf("Expected %d, got %d\n", tc.expected, actual)
		}
	}
}

func Test_Sub(t *testing.T) {
	t.Parallel()

	t.Log("running Test_Add")

	type test struct {
		a        int
		b        int
		expected int
	}

	tests := []test{
		{1, 2, -1},
		{2, 3, -1},
		{7, 5, 2},
	}

	for _, tc := range tests {
		actual := paralleltesting.Sub(tc.a, tc.b)

		if actual != tc.expected {
			t.Fatalf("Expected %d, got %d\n", tc.expected, actual)
		}
	}
}
