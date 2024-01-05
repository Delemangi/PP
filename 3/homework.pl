ime(teo).
ime(mira).
ime(bruno).
ime(igor).

hrana(sendvich).
hrana(pita).
hrana(hamburger).
hrana(pica).

hobi(krstozbori).
hobi(pishuvanje).
hobi(chitanje).
hobi(fotografija).

maica(bela).
maica(zolta).
maica(sina).
maica(crvena).

pozicija(1).
pozicija(2).
pozicija(3).
pozicija(4).

devojka(mira).

%% Helpers

next_to(P1, P2) :- P1 is P2 + 1.
next_to(P1, P2) :- P1 is P2 - 1.

position(T, P) :- position_assertion(T, P, []).

%% 1. Тео седи најлево и јаде сендвич.
assertion1(tuple(teo, sendvich, _, _), 1) :- position(tuple(teo, sendvich, _, _), 1).

position_assertion(tuple(teo, sendvich, _, _), 1, _).

%% 2. Мира сака да решава крстозбори и ужива во јадењето пита.
assertion2(tuple(mira, pita, krstozbori, _), _).

%% 3. Девојката има бела маица.
assertion3(tuple(Ime, _, _, bela), _) :- devojka(Ime).

%% 4. Бруно има жолта маица.
assertion4(tuple(bruno, _, _, zolta), _).

%% 5. Оној што сака да пишува јаде хамбургер.
assertion5(tuple(_, hamburger, pishuvanje, _), _).

%% 6. Личноста која јаде пита седи покрај Тео.
assertion6(tuple(_, pita, _, _), Pos) :- pozicija(Pos), pozicija(PosTeo), position(tuple(_, pita, _, _), Pos), position_assertion(tuple(teo, _, _, _), PosTeo, [Pos]), next_to(Pos, PosTeo).
position_assertion(tuple(_, pita, _, _), Pos, AntiLoop) :- pozicija(Pos), pozicija(PosTeo), not(member(PosTeo, AntiLoop)), append(AntiLoop, [PosTeo], NewAntiLoop), position_assertion(tuple(teo, _, _, _), PosTeo, NewAntiLoop), next_to(Pos, PosTeo).

%% 7. Бруно седи покрај оној што јаде пица.
assertion7(tuple(bruno, _, _, _), Pos) :- pozicija(Pos), pozicija(PosPica), position(tuple(bruno, _, _, _), Pos), position_assertion(tuple(_, pica, _, _), PosPica, [Pos]), next_to(Pos, PosPica).
position_assertion(tuple(bruno, _, _, _), Pos, AntiLoop) :- pozicija(Pos), pozicija(PosPica), not(member(PosPica, AntiLoop)), append(AntiLoop, [PosPica], NewAntiLoop), position_assertion(tuple(_, pica, _, _), PosPica, NewAntiLoop), next_to(Pos, PosPica).

%% 8. Личноста која седи покрај онаа во бела маица сака пица.
assertion8(tuple(_, pica, _, _), Pos) :- pozicija(Pos), pozicija(PosBela), position(tuple(_, pica, _, _), Pos), position_assertion(tuple(_, _, _, bela), PosBela, [Pos]), next_to(Pos, PosBela).
position_assertion(tuple(_, pica, _, _), Pos, AntiLoop) :- pozicija(Pos), pozicija(PosBela), not(member(PosBela, AntiLoop)), append(AntiLoop, [PosBela], NewAntiLoop), position_assertion(tuple(_, _, _, bela), PosBela, NewAntiLoop), next_to(Pos, PosBela).

%% 9. Игор сака да чита.
assertion9(tuple(igor, _, chitanje, _), _).

%% 10. Сина маица има личноста која седи десно од девојката.
assertion10(tuple(_, _, _, sina), Pos) :- devojka(Devojka), pozicija(Pos), pozicija(PosDevojka), position(tuple(_, _, _, sina), Pos), position_assertion(tuple(Devojka, _, _, _), PosDevojka, [Pos]), Pos > PosDevojka.
position_assertion(tuple(_, _, _, sina), Pos, AntiLoop) :- devojka(Devojka), pozicija(Pos), pozicija(PosDevojka), not(member(PosDevojka, AntiLoop)), append(AntiLoop, [PosDevojka], NewAntiLoop), position_assertion(tuple(Devojka, _, _, _), PosDevojka, NewAntiLoop), Pos > PosDevojka.

%% Solution Helpers

find_tuple(Condition, [Tuple|_], Depth) :- position(Tuple, Depth), call(Condition, Tuple, Depth).
find_tuple(Condition, [_|Other], Depth) :- NextDepth is Depth + 1, find_tuple(Condition, Other, NextDepth).

tuple_3(Condition, [_, _, T, _]) :- call(Condition, T).
tuple_4(Condition, [_, _, _, T]) :- call(Condition, T).

assert_not_equal(A, B, C, D) :- dif(A, B), dif(A, C), dif(A, D), dif(B, C), dif(B, D), dif(C, D).

assert_tuples_not_equal([tuple(A1, B1, C1, D1), tuple(A2, B2, C2, D2), tuple(A3, B3, C3, D3), tuple(A4, B4, C4, D4)]) :- assert_not_equal(A1, A2, A3, A4), assert_not_equal(B1, B2, B3, B4), assert_not_equal(C1, C2, C3, C4), assert_not_equal(D1, D2, D3, D4).

assert_position([T1, T2, T3, T4]) :- position(T1, 1), position(T2, 2), position(T3, 3), position(T4, 4).

define_tuple(tuple(Ime, Hrana, Hobi, Maica)) :- ime(Ime), hrana(Hrana), hobi(Hobi), maica(Maica).

define_solution([T1, T2, T3, T4]) :- define_tuple(T1), define_tuple(T2), define_tuple(T3), define_tuple(T4).

%% Solution

reshenie(L) :- assert_tuples_not_equal(L), assert_position(L), find_tuple(assertion1, L, 1), find_tuple(assertion2, L, 1), find_tuple(assertion3, L, 1), find_tuple(assertion4, L, 1), find_tuple(assertion5, L, 1), find_tuple(assertion6, L, 1), find_tuple(assertion7, L, 1), find_tuple(assertion8, L, 1), find_tuple(assertion9, L, 1), find_tuple(assertion10, L, 1), !, define_solution(L).
