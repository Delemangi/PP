%% 1

lice(1,petko,petkovski,m,datum(1,3,1950),kratovo,skopje).
lice(2,marija,petkovska,z,datum(30,5,1954),kumanovo,skopje).
lice(3,ljubica,petkovska,z,datum(29,11,1965),skopje,skopje).
lice(4,vasil,vasilev,m,datum(8,4,1954),bitola,bitola).
lice(5,elena,vasileva,z,datum(19,6,1958),resen,bitola).
lice(6,krste,krstev,m,datum(9,8,1948),veles,veles).
lice(7,biljana,krsteva,z,datum(13,8,1949),veles,veles).
lice(8,igor,krstev,m,datum(26,10,1971),veles,skopje).
lice(9,kristina,krsteva,z,datum(30,5,1974),kumanovo,skopje).
lice(10,julija,petrova,z,datum(30,5,1978),skopje,skopje).
lice(11,bosko,petkovski,m,datum(13,11,1981),skopje,skopje).
lice(12,gjorgji,vasilev,m,datum(15,7,1978),bitola,bitola).
lice(13,katerina,petkovska,z,datum(11,12,1979),bitola,skopje).
lice(14,petar,vasilev,m,datum(21,2,1982),skopje,skopje).
lice(15,andrej,krstev,m,datum(3,8,1998),skopje,skopje).
lice(16,martina,petkovska,z,datum(5,12,2005),skopje,skopje).

familija(1,2,[9,10]).
familija(1,3,[11]).
familija(4,5,[12,13,14]).
familija(6,7,[8]).
familija(8,9,[15]).
familija(11,13,[16]).

%% 1 a

generate_rodeni_razlicen_grad(X) :- lice(X, _, _, _, _, A, _), lice(R1, _, _, _, _, B, _), lice(R2, _, _, _, _, C, _), familija(R1, R2, D), A \= B, A \= C, member(X, D).

rodeni_razlicen_grad(N) :- findall(X, generate_rodeni_razlicen_grad(X), L), length(L, N).

%% 1 б

diff_less_than_week(D) :- D =< 7.
diff_less_than_week(D) :- D >= 30 * 12 - 7.

diff_ignore_year(datum(D1, M1, _), datum(D2, M2, _)) :-
    T1 is M1 * 30 + D1,
    T2 is M2 * 30 + D2,
    D is abs(T1 - T2),
    diff_less_than_week(D).

parent(A, B) :- familija(A, _, L), member(B, L).
parent(A, B) :- familija(_, A, L), member(B, L).

ancestor(A, B) :- parent(A, B), !.
ancestor(A, B) :- parent(A, C), ancestor(C, B).

generate_predci(S, A) :- lice(S, _, _, G, D1, _, _), lice(A, _, _, G, D2, _, _), diff_ignore_year(D1, D2), ancestor(A, S).

predci(S, L) :- findall(A, generate_predci(S, A), L).

%% 2

telefon(111111,petko,petkovski,[povik(222222,250),povik(101010,125)]).
telefon(222222,marija,petkovska,[povik(111111,350),povik(151515,113),povik(171717,122)]).
telefon(333333,ljubica,petkovska,[povik(555555,150),povik(101010,105)]).
telefon(444444,vasil,vasilev,[povik(171717,750)]).
telefon(555555,elena,vasileva,[povik(333333,250),povik(101010,225)]).
telefon(666666,krste,krstev,[povik(888888,75),povik(111111,65),povik(141414,50),povik(161616,111)]).
telefon(777777,biljana,krsteva,[povik(141414,235)]).
telefon(888888,igor,krstev,[povik(121212,160),povik(101010,225)]).
telefon(999999,kristina,krsteva,[povik(666666,110),povik(111111,112),povik(222222,55)]).
telefon(101010,julija,petrova,[]).
telefon(121212,bosko,petkovski,[povik(444444,235)]).
telefon(131313,gjorgji,vasilev,[povik(141414,125),povik(777777,165)]).
telefon(141414,katerina,petkovska,[povik(777777,315),povik(131313,112)]).
telefon(151515,petar,vasilev,[]).
telefon(161616,andrej,krstev,[povik(666666,350),povik(111111,175),povik(222222,65),povik(101010,215)]).
telefon(171717,martina,petkovska,[povik(222222,150)]).

sms(111111,[222222,999999,101010]).
sms(444444,[333333,121212,161616]).
sms(111111,[777777]).
sms(666666,[888888]).
sms(444444,[555555,121212,131313,141414]).
sms(666666,[777777,888888]).
sms(888888,[999999,151515]).
sms(171717,[131313,161616]).

%% 2 а

generate_calls(B) :- telefon(_, _, _, L), member(povik(B, _), L).

count_calls(B, N) :- findall(B, generate_calls(B), L), length(L, N).

generate_call_counts(N) :- telefon(B, _, _, _), count_calls(B, N).

najbroj(X, Y) :- findall(C, generate_call_counts(C), CS), max_list(CS, M), telefon(B, X, Y, _), count_calls(B, M).

%% 2 б

% O: Other, C: Count
count(X, [X|O], C) :- !, count(X, O, C1), C is C1 + 1.
count(X, [_|O], C) :- count(X, O, C).
count(_, [], 0).

% N: Number O: Other D: Duration T: Total
total_calls_duration(N, [_|O], D) :- total_calls_duration(N, O, D).
total_calls_duration(N, [povik(B, D)|O], T) :- !, total_calls_duration(N, O, T1), T is T1 + D.
total_calls_duration(_, [], 0).

% O: Other C: Count H: Head
count_members(X, [_|O], C) :- count_members(X, O, C).
count_members(X, [H|O], C) :- member(X, H), !, count_members(X, O, C1), C is C1 + 1.
count_members(_, [], 0).

% Receiver Count
count_sms(X, R, C) :- findall(L, sms(X, L), Re), count_members(R, Re, C).

% T: Total C: Calls D: Duration
outgoing(X, Y, T) :- telefon(X, _, _, P), member(povik(Y, _), P), total_calls_duration(Y, P, D), count_sms(X, Y, Count), T is D + 100 * Count.
outgoing(X, Y, T) :- count_sms(X, Y, C), T is 100 * C.

% T: Total
total_duration(X, Y, T) :- outgoing(X, Y, T1), outgoing(Y, X, T2), T is T1 + T2.

% D: Duration
generate_durations(X, D) :- telefon(P, _, _, _), total_duration(X, P, D).

% D: Duration M: Max S: Durations
omilen(X, Y) :- findall(D, generate_durations(X, D), S), max_list(S, M), telefon(Y, _, _, _), total_duration(X, Y, M).

%% 3

klient(1,petko,petkov,[usluga(a,b,50,datum(12,12,2015),23),usluga(c,a,50,datum(7,12,2015),34),usluga(c,f,40,datum(7,11,2015),23)]).
klient(2,vasil,vasilev,[usluga(a,e,50,datum(25,12,2015),12),usluga(c,g,40,datum(17,11,2015),56),usluga(g,d,50,datum(17,12,2015),45),usluga(e,a,40,datum(24,12,2015),34)]).
klient(3,krste,krstev,[usluga(c,b,60,datum(31,12,2015),56),usluga(e,f,60,datum(31,12,2015),34)]).
klient(4,petar,petrov,[usluga(a,f,50,datum(25,12,2015),23),usluga(f,d,50,datum(25,12,2015),34)]).
klient(5,ivan,ivanov,[usluga(d,g,50,datum(7,12,2015),56),usluga(g,e,40,datum(25,12,2015),34)]).
klient(6,jovan,jovanov,[usluga(c,f,50,datum(5,12,2015),12),usluga(f,d,50,datum(27,12,2015),45)]).
klient(7,ana,aneva,[usluga(e,d,50,datum(11,12,2015),12),usluga(d,g,50,datum(11,12,2015),12)]).
klient(8,lidija,lideva,[usluga(e,g,50,datum(29,12,2015),45),usluga(f,b,50,datum(29,12,2015),34)]).

rastojanie(a,b,4).
rastojanie(a,c,7).
rastojanie(b,c,5).
rastojanie(b,d,3).
rastojanie(c,d,4).
rastojanie(b,e,6).
rastojanie(c,e,2).
rastojanie(b,f,8).
rastojanie(e,f,5).
rastojanie(f,g,3).

%% 3 а

% L: Location S: Services N: Number
generate_clients(L, K) :- klient(K, _, _, S), member(usluga(L, _, _, _, _), S).
generate_clients(L, K) :- klient(K, _, _, S), member(usluga(_, L, _, _, _), S).

izbroj_lokacija(L, N) :- findall(K, generate_clients(L, K), S), length(S, N).

%% 3 б

% F: From T: To D: Distance V: Visited M: Middle N: New Ds: Distances O: Other T: Total S: Services
direct_distance(F, T, D) :- rastojanie(F, T, D).
direct_distance(F, T, D) :- rastojanie(T, F, D).

generate_service_distances_helper(F, T, V, D) :- not(member(F, V)), append(V, [F], N), !, direct_distance(F, M, D1), generate_service_distances_helper(M, T, N, D2), D is D1 + D2.
generate_service_distances_helper(F, T, V, D) :- not(member(F, V)), direct_distance(F, T, D), !.

generate_service_distances(F, T, D) :- generate_service_distances_helper(F, T, [], D).

minimum_distance(F, T, D) :- findall(D1, generate_service_distances(F, T, D1), Ds), min_list(Ds, D).

total_distances([usluga(F, T, _, _, _)|O], T) :- minimum_distance(F, T, D), total_distances(O, R), T is R + D.
total_distances([], 0).

generate_total_distances(T) :- klient(_, _, _, S), total_distances(S, T).

najmnogu_kilometri(X, Y) :- findall(T, generate_total_distances(T), Ds), max_list(Ds, Max), klient(_, X, Y, S), total_distances(S, Max).

%% 3 в

% F: From T: To P: Price PKM: Price Per Kilometer D: Distance V: Vehicle S: Services
price_service(F, T, PKM, P) :- minimum_distance(F, T, D), P is PKM * D.

generate_vehicle_price(V, P) :- klient(_, _, _, S), member(usluga(F, T, PKM, datum(_, 12, 2015), V), S), price_service(F, T, PKM, P).

generate_service_vehicle(V) :- klient(_, _, _, S), member(usluga(_, _, _, _, V), S).

total_price(V, T) :- findall(P, generate_vehicle_price(V, P), Ps), sum_list(Ps, T).

generate_total_price(T) :- generate_service_vehicle(V), total_price(V, T).

najmnogu_zarabotil(V) :- findall(T, generate_total_price(T), TP), max_list(TP, M), generate_service_vehicle(V), total_price(V, M), !.
