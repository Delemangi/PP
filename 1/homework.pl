%% 1
even([]).
even([_|T]) :- odd(T).
odd([_|T]) :- even(T).

neparen_palindrom(T) :- odd(T), reverse(T, T).

%% 2
is_length([], 0).
is_length([_|O], T) :- T1 is T - 1, is_length(O, T1).

sub_array(_, 0, []).
sub_array([X|O], L, [X|R1]) :- L =\= 0, !, L1 is L - 1, sub_array(O, L1, R1).

sub_arrays(A, L, []) :- L1 is L - 1, is_length(A, L1).
sub_arrays([X|O], L, [Y|RO]) :- sub_array([X|O], L, Y), sub_arrays(O, L, RO).

count([], _, 0).
count([X|O], X, C) :- !, count(O, X, C1), C is C1 + 1.
count([_|O], X, C) :- !, count(O, X, C).

max(A, B, A) :- A >= B.
max(A, B, B) :- A < B.

max_count(_, [], 0).
max_count(S, [X|O], C) :- max_count(S, O, C1), count(S, X, C2), max(C1, C2, C).
max_count(S, C) :- max_count(S, S, C).

find_with_count(S, [L|_], L, MC) :- count(S, L, MC), !.
find_with_count(S, [_|O], L, MC) :- find_with_count(S, O, L, MC).

naj_podniza(A, C, L) :- sub_arrays(A, C, S), max_count(S, MC), find_with_count(S, S, L, MC).

%% 3
proveri([A, B]) :- !, A < B.
proveri([A, B, C|O]) :- A < B, B > C, proveri([C|O]).

%% 4
element([E|_], E).
element([_|O], E) :- element(O, E).

permutation([], []).
permutation(A, [E|O]) :- element(A, E), delete(A, E, A1), permutation(A1, O).

permutacii(A, R) :- findall(P, permutation(A, P), R).

%% 5
add_common(O1, O2, R, [B|L]) :- B is R mod 2, C1 is R // 2, add(O1, O2, C1, L).
add([], [], 0, []).
add([], [], 1, [1]).
add([], [X2|O2], C, L) :- R is X2 + C, add_common([], O2, R, L).
add([X1|O1], [], C, L) :- R is X1 + C, add_common(O1, [], R, L).
add([X1|O1], [X2|O2], C, L) :- R is X1 + X2 + C, add_common(O1, O2, R, L).

sub_carry(-1, -1) :- !.
sub_carry(_, 0).
sub_common(O1, O2, R, [B|L]) :- B is R mod 2, sub_carry(R, C1), sub(O1, O2, C1, L).
sub([], [], 0, []).
sub([], [X2|O2], C, L) :- R is -X2 + C, sub_common([], O2, R, L).
sub([X1|O1], [], C, L) :- R is X1 + C, sub_common(O1, [], R, L).
sub([X1|O1], [X2|O2], C, L) :- R is X1 - X2 + C, sub_common(O1, O2, R, L).
trim_zeros([1|O], [1|O]) :- !.
trim_zeros([0], [0]) :- !.
trim_zeros([0|O], L) :- trim_zeros(O, L).

mul_rec(_, 0, 0) :- !.
mul_rec(A, 1, A) :- !.
mul_rec(A, C, L) :- C1 is C - 1, mul_rec(A, C1, L1), mul_rec(A, C1, L2), sobiranje(L1, L2, L).
mul(_, [], _, [0]).
mul(A, [1|O], C, L) :- C1 is C + 1, mul(A, O, C1, L1), mul_rec(A, C1, L2), sobiranje(L1, L2, L).
mul(A, [0|O], C, L) :- C1 is C + 1, mul(A, O, C1, L).

length_less_or_equal([], []).
length_less_or_equal([0|O1], [_|O2]) :- length_less_or_equal(O1, O2).
length_less_or_equal([1|O1], [1|O2]) :- length_less_or_equal(O1, O2).
less_or_equal(A, B) :- length(A, L1), length(B, L2), L1 < L2, !.
less_or_equal(A, B) :- length(A, L), length(B, L), length_less_or_equal(A, B).

sobiranje(A, B, L) :- reverse(A, A1), reverse(B, B1), add(A1, B1, 0, L1), reverse(L1, L).
odzemanje(A, B, L) :- reverse(A, A1), reverse(B, B1), sub(A1, B1, 0, L1), reverse(L1, L2), trim_zeros(L2, L).
mnozenje(A, B, L) :- reverse(B, B1), mul(A, B1, 0, L).
delenje(A, B, L) :- less_or_equal(B, A), odzemanje(A, B, R), delenje(R, B, L1), sobiranje(L1, [1], L), !.
delenje(_, _, [0]) :- !.

%% 6
list_multiply([H1|O1], [H2|O2], [R|L]) :- list_multiply(O1, O2, L), R is H1 * H2.
list_multiply([], [], []).

list_product(A, B, R) :- list_multiply(A, B, R1), sumlist(R1, R).

list_calc([Row1|O], Row2, [E|L]) :- list_calc(O, Row2, L), list_product(Row1, Row2, E).
list_calc([], _, []).

presmetaj(M, R) :- presmetaj(M, M, R).
presmetaj(M, [Row|O], [R|L]) :- presmetaj(M, O, L), list_calc(M, Row, R).
presmetaj(_, [], []).

%% 7
prepend(A, X, [X|A]).

should_go_left(A, B) :- length(A, L1), length(B, L2), L1 < L2, !.
should_go_left(A, B) :- length(A, L), length(B, L), should_go_left_same_len(A, B).

should_go_left_same_len([], []).
should_go_left_same_len([H|O1], [H|O2]) :- !, should_go_left_same_len(O1, O2).
should_go_left_same_len([H1|_], [H2|_]) :- H1 < H2.

sort(L, L, 0) :- !.
sort(A, L, C) :- sort_helper(A, L1), C1 is C - 1, sort(L1, L, C1).

sort_helper([A, B|O1], L) :- should_go_left(A, B), !, sort_helper([A|O1], L1), prepend(L1, B, L).
sort_helper([A, B|O1], L) :- !, sort_helper([B|O1], L1), prepend(L1, A, L).
sort_helper([A], [A]).

unique([A, A|O1], [A|O2]) :- !, unique([A|O1], [A|O2]).
unique([A|O1], [A|O2]) :- unique(O1, O2).
unique([], []).

transform(A, L) :- length(A, C), sort(A, L1, C), unique(L1, L).

%% 8
delete([H|O], [R1|R], S, NS) :- is_list(H), !, delete(H, R1, S, S1), delete(O, R, S1, NS).
delete([H1|O1], [H1|O2], S, NS) :- not(member(H1, S)), !, append(S, [H1], S1), delete(O1, O2, S1, NS).
delete([H1|O1], O2, S, NS) :- delete(S, H1, S1), delete(O1, O2, S1, NS).
delete([], [], S, S).

brisi_sekoe_vtoro(L, R) :- delete(L, R, [], _).
