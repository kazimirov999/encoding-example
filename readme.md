Юникод — это стандарт кодирования символов для большинства языков мира, включая вымершие, а также много различных вспомогательных символов (например, математических). Юникод разрабатывает одноименный консорциум (www.unicode.org). 
У каждого символа есть официальное именование (например, «latin small letter a») и код (code point) — число от 0 до 10FFFF.

Символ (Character) — наименьший компонент письменного языка, который имеет семантическое значение. Еще одно значение символа — абстрактный символ — единица информации, используемая для управления, организации и для представления текстовой информации.
Глиф — графическое представление символа. Некоторые символы могут иметь различное начертание, например в зависимости от позиции в слове. Но это уже проблемы системы вывода на экран. Сборище глифов, обычно одного стиля, называют шрифтом.

Character set — Набор элементов для представления текстовой информации. Т.е. это просто набор любых символов, например строчные буквы английского алфавита.

Coded character set — character set, у которого каждому элементу присвоен числовой код (code point).

С charset немного сложнее — юникод определяет его просто как синоним coded character set (www.unicode.org/...x.html#charset, а в java это означает encoding — правило отображения кодов символов (code points) в кодовые единицы (code units).

Code point (кодовая точка)- любое значение в пространстве кодов юникода, т.е. сами коды символов.
Code unit (кодовая единица)- битовые последовательности, с помощью которых можно закодировать code point. В юникод используют 3 типа code unit — 8ми битовые (byte), 16ти битовые и 32х битовые.
В большинстве языков программирования (включая java) для внутреннего представления (класс String) используют 16 битные code units, т.е UTF-16. Поэтому, когда вы у строки вызываете метод length, вы получите длину в кодовых единицах, а не количество символов в строке.

Переходим к самому интересному :)
UTF — Unicode Transformation format — способ кодирования кодов символов в последовательности байт.
Есть формы кодирования UTF, а есть схемы. И вообще говоря это не одно и то же. 
Формы кодирования — правила разложения кода символа в кодовые единицы (code units). А схема кодирования — правила сериализации кодовых единиц в байты (например, при записи на диск).
Есть 3 формы кодирования — UTF-8, UTF-16, UTF-32.
В UTF-32 каждый код символа кодируется 4мя байтами, т.е. это кодирование с фиксированной длиной (fixed length encoding).
В UTF-8 каждый символ юникода кодируется последовательностью от одного до 4х байт по хитрой схеме (en.wikipedia.org/...-8#Description, т.е. это variable length encoding. 
Преимущества данного метода в том, что коды ascii (кодировка, придуманная американцами, соответствуе первым 127 символам в наборе unicode) кодируются без изменений, одним байтом.
В UTF-16 для кодирования кодов символов с номерами больше U+FFFF (их называют supplementary characters) используют суррогатные пары — коды из диапазона U+D800-U+DFFF. Т.е., символы с кодами больше U+10000 кодируются двумя 16ти битными парами. Коды из диапазона суррогатых пар не должны встречаться поодиночке. Пространство кодов с кодами U+0000 — U+FFFF называют Basic Multilingual Plane (BMP). 
Схем кодирования семь — UTF-8, UTF-16, UTF-16BE, UTF-16LE, UTF-32, UTF-32BE, UTF-32LE.
BE и LE означают big endian или little endian, т.е. в каком порядке идут байты, сначала старшие или сначала младшие. Если не указано и нет BOM, то следует трактовать как BE.
BOM — byte order mark — отметка порядка байтов. Символ с кодом FEFF (неразрывный непечатный пробел) записывают в начале текста. При вычитке проверяют первый байт, если это BOM и код FFFE, значит порядок little endian. Если код FEFF или это не BOM, то порядок big endian.
Никто не запрещает записать BOM в текст, закодированный в UTF-8 — но делать так не стоит, особенно в unix-подобных системах. У Microsoft особое мнение по этому поводу,поэтому notepad таки вставляет в текст в UTF-8 BOM :)
Если вы такой текст прочитаете в java, получите 3 кракозябла в начале.

Существует также стандарт кодирования от ISO/IEC, ISO-10646, который совместим в юникодом по кодам символов и названием этих символов

===================================
Символ (character) — единица информации, которая соответствует письменному символу (графема) естественного языка общения. «?», «A», «+», к примеру. 
Каждая из графем (или набор графем), отображенных на Вашем мониторе, представляет собой глиф. Глиф (glyph), как единица графики, является графическим отображением графемы. Glyph символа процента или «собака», в конкретном случае, выглядят как «%», «@».
Определенный стиль отображения глифа, с учетов размера, насыщенности, удобочитаемости и других характерных особенностей, это и есть шрифт (font). Примером современных компьютерных font’ов могут быть всем известные Arial, Calibri, Times New Roman и др.
В промежутке между тем как символ языка общения в виде character отобразится на мониторе (к примеру) в виде glyph’а определенного font’а, происходит «конклав» кодировки. Процесс перевода каждого character на машинный язык требует определенного стандарта. 
Coded character set определяет как представить character в виде целого числа, которое называется code point. Для примера, символ представляющий большую букву «А» (лат.) имеет номер 65 (в ASCII). Один из первых coded character set — ASCII (American Standard Code for Information Interchange) — американский кодировочный стандарт для печатных символов и некоторых специальных кодов. ASCII представляет собой кодировку из 128 символов для представления цифр, латинских символов, знаков препинания и управляющих символов, каждому символу соответствует 7-ми битное значение целого числа. В целом на этом можно было бы и остановиться, если бы не наличие многих других символов различных письменностей, которые в ASCII учтены не были, да и 7-ми битная кодировка нравилась не всем. Что привело к развитию других систем кодирования символов — возникла, как минимум, проблема совместимости. 
Таким единым стандартом является Unicode (юникод) — стандарт кодирования символов, который представляет практически все возможные знаки письменности. Изначально кодовое пространство Unicode включало 65536 code points. В дальнейшем кодовое пространство Юникода было расширено до 1 112 064 code points. Где первые 65 536 - первоначальное пространство 16-битных представлений символов, 2048 — количество значений забронированных для элементов суррогатных пар (от 55 296 до 57 343), 1 048 576 — пространство для символов не вошедших в первый диапазон (Basic Multilingual Plane) — supplementary characters. В итоге пространство Юникода разбилось на 17 плоскостей, где 0-ая плоскость — Basic Multilingual Plane — со всеми наиболее часто используемыми символами. 
Один из методов, с помощью которого Юникод мапится на character set — Unicode Transformation Format encoding (UTF) . Этот метод породил 3 известные кодировки: UTF-8, UTF-16, UTF-32

А что такое этот самый «UTF», если «их» так много?
>> [65]
>> [-2, -1, 0, 65]
>> [0, 65]
>> [65, 0]
>> [0, 0, 0, 65]
>> [0, 0, 0, 65]
>> [65, 0, 0, 0]

>> [65] — использована кодировка UTF-8 — 8-ми битный формат преобразования Юникода, что для символа «A» являет собой один байт и совпадает со значением кодировки ASCII. Первые символы Unicode полностью соответствуют кодировке ASCII.

>> [-2, -1, 0, 65] — кодировка UTF-16 — 16-ти битный формат преобразования Юникода. UTF-16 имеет представления UTF-16BE и UTF-16LE, что значит Bid Endian и Little Endian — по сути требование компьютерной архитектуры, которая использует многобайтные значения со старшим байтом в начале (Little Endian) или старшим байтом в конце (Big Endian). BE и LE — метки порядка байт или понятным языком byte order mark — BOM. 
По умолчанию UTF-16 использует Big Endian, чтобы неявно это обозначить, в начало строки (в нашем случае «A») вставляется два байта обозначающие то самое Big Endian. Эти байты [-2, −1] в начале сроки, U+FEFF и U+FFFE в шестнадцатеричном представлении — непечатные символы в Unicode. 
>> [0, 65] >> [65, 0] — теперь легко можно понять порядок байт и отсутствие BOM в начале строки.
>> [0, 0, 0, 65] >> [0, 0, 0, 65] >> [65, 0, 0, 0] — такая же ситуация как и в случае с использованием кодировки UTF-16 за исключением того что кодировка UTF-32 — система кодирования фиксированной длины Fixed width encoding — каждый символ Юникода должен быть представлен в виде 32 бит. Преимущество UTF-32 заключается в том что каждый code point представляет конкретный code point юникода. Тем не менее, UTF-32 достаточно тяжеловесный по тем же причинам.

Почему строка в которой в два раза больше букв (2 вместо 1) занимает в только 1.5 раза больше места?
System.out.println("A".getBytes("UTF-16").length);
System.out.println("AA".getBytes("UTF-16").length);
>> 4
>> 6
>> 4 — очевидно почему 4 байта(неявное указание BE занимает первые 2 байта).
>> 6 — каждый следующий символ кодируется 2-мя байтами, как это и предусмотрено системой кодирования UTF-16, поэтому добавление следующего такого же символа добавляет всего два байта.

Если UTF-8 так хорош (как все говорят), почему он занимает так много места (и чем он тогда хорош)?
System.out.println("ЭЮЯ".getBytes("UTF-8").length);
System.out.println("ЭЮЯ".getBytes("latin1").length);
>> 6
>> 3
>> 6 — каждый символ кодирован 2мя байтами т.к. символы «ЭЮЯ» выходят за пределы однобайтного представления в UTF-8.
>> 3 — каждый символ кодирован 1м байтом т.к. символы «ЭЮЯ» кодируются cp1251 — широко используемая 8-ми битная кодировка (подобно ASCII) для кириллических символов. 
Как строка из одного символа может сохраняться в разные размеры байтовых массивов
char ch;
ch = 0x0001;
ch = 0x0111;
ch = 0x1111;
>> [1]
>> [-60, -111]
>> [-31, -124, -111]
Учитывая то, что UTF-8 (как и UTF-16) — Variable width encoding — система кодирования, использующая коды различной длинны для представления символов. 
ch = 0×0001 — входит в диапазон code points 0×0000 — 0×007F, который представляет символы одним байтом — 0xxxxxxx.
ch = 0×0111 — code points 0×0080 — 0×07FF — 2х байтное представление 110xxxxx 10xxxxxx.
ch = 0×1111 — code points 0×0800 — 0xFFFF — 3х байтное представление 1110xxxx 10xxxxxx 10xxxxxx.
Где каждый байт — Code unit — минимальный набор бит, который представляет единицу кодированного текста. Для UTF-16 — code unit состоит из 16 бит и из 32х для UTF-32.

Все таки в этой строке ОДИН символ или ДВА?
>> �
>> 2
>> 1
>> ?
>> ?
В этой строке один символ.
Метод length() возвращает кол-во знаков char в строке. И если бы, допустим, мы на основе этого метода пытались выделить количество ячеек для имени китайца на китайском языке, то ячеек всегда оказывалось бы больше чем китайцу нужно. 
Метод codePointCount(0, 2) вернул количество символов соответствующих стандарту Юникод(то что нужно китайцу) — 1 символ. Символы, которые лежат выше BMP или следующие за code point U+FFFF (65535) называются supplementary characters. Это символы Юникода — пары 16-битных знаков char, которые называются суррогатными парами (surrogate pair). Два знака char — суррогатное представление символов Юникода в диапазоне от U+10000 до U+10FFFF.