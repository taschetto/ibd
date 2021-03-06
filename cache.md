Considere um buffer com 4 frames e as seguintes requisições de ~~datablocks~~ registros:

`A;B`

Onde A é o endereço do datablock e B é a posição do registro no datablock.

`8;3 8;5 4;2 3;1 4;7 8;5 5;8 9;3 8;2 10;1 4;7 8;2 10;2 11;5 12;0 8;2 10;1 7;2 10;3 8;9`

# FIFO

Implementado com um array e um ponteiro.

Frame |   1  |   2  |   3  |   4  |   5  |   6  |   7  |   8  |   9  |  10  |  12  |  13  |  14  |
----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
F0    | ~~8~~|      |      |      |~~9~~ |      |      |      |~~11~~|      |      |      |   7  |
F1    |      |~~4~~ |      |      |      |~~8~~ |      |      |      |  12  |      |      |      |
F2    |      |      |~~3~~ |      |      |      |~~10~~|      |      |      |  8   |      |      |
F3    |      |      |      |~~5~~ |      |      |      |~~4~~ |      |      |      |  10  |      |

Hits: 6
Misses: 14

# LRU

Implementado com uma lista duplamente encadeada.

Frame |   1  |   2  |   3  |   4  |   5  |   6  |   7  |   8  |   9  |  10  |  12  |  13  |  14  |  15  |  16  |  17  |  18  |  19  |  20  |  21  |
----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
F0    | ~~8~~|~~8~~ |      |      |      |~~8~~ |      |      | ~~8~~|      |      |~~8~~ |      |      |      |~~8~~ |      |      |      |  8   |
F1    |      |      | ~~4~~|      | ~~4~~|      |      |      |      |~~10~~|      |      |~~10~~|      |      |      |~~10~~|      | 10   |      |
F2    |      |      |      |~~3~~ |      |      |      |~~9~~ |      |      |      |      |      |~~11~~|      |      |      |  7   |      |      |
F3    |      |      |      |      |      |      |~~5~~ |      |      |      | ~~4~~|      |      |      |  12  |      |      |      |      |      |

Hits: 10
Misses: 10

# Clock

Também conhecido como "second chance".

Entra com USED=0 (*) até encher o buffer. Depois, entra com USED=1.

Ponteiro não se mexe quando é HIT.

Frame |   1         |   2     |   3  |   4  |   5  |   6  |   7  |   8  |   9  |  10  |
----- | -----       | ----    | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
F0    |>>>>>8*¹¹*¹¹*|  11¹*   |      |  10¹ |      |      |      |      |      |      |
F1    |>>>>>4*¹*¹*  |  12¹*   |      |      |      |      |      |      |      |      |
F2    |>>>>>3*      |  9¹*    |  8¹*¹|      |      |      |      |      |      |      |
F3    |>>>>5*       | 10¹¹*¹* |      |      |      |      |      |      |      |      |

Hits: 9
Misses: 11
