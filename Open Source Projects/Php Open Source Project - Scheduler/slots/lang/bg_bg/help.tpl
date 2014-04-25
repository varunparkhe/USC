{*
Copyright 2011-2014 Nick Korbel

This file is part of Booked Scheduler.

Booked Scheduler is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Booked Scheduler is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Booked Scheduler.  If not, see <http://www.gnu.org/licenses/>.
*}
{include file='globalheader.tpl'}
<h1>Booked Scheduler Помощ</h1>

<div id="help">
<h2>Регистрация</h2>

<p>
	За да използвате Booked Scheduler се изисква регистрация, ако вашия администратор е разрешил това. След регистриране на
	вашия профил,
	ще можете да влезете и да достъпвате всички ресурси, за които имате права.
</p>

<h2>Резервация</h2>

<p>
	Под меню Разписание ще намерите елемент Резервация. Тук ще видите наличните, резервираните и
	блокираните времени интервали в разписанието и ще ви даде възможност да резервирате ресурси,
	за които имате права.
</p>

<h3>Експресен</h3>

<p>
	На страница Резервации, намерете ресурса, датата и часа, които искате да резервирате. Кликвайки върху времевия слот ще можете да
	промените детайлите на резервацията. Кликането върху
	бутон Създаване ще задейства проверка за наличност, резервиране и изпращане на имейли. Ще ви бъде даден
	референтен номер за проследяване на резервацията.
</p>

<p>Всички промени, направени в една резервация, няма да влязат в сила, докато не запазите резервацията.</p>

<p>Само администраторите на приложението могат да създават резервации в миналото.</p>

<h3>Множество ресурси</h3>

<p>Вие можете да  резервирате всички ресурси, до които имате достъп, като част от една резервация. За да добавите допълнителни ресурси
	към вашата резервация, кликнете върху линк Повече ресурси, до името на основния ресурс, който резервирате.
	Вие можете да добавяте още ресурси, като ги изберете и кликнете върху бутон Готово.</p>

<p>За да премахнете допълнителни ресурси от вашата резервация, кликнете върху линк Още ресурси, размаркирате ресурсите,
	които искате да премахнете и щракнете върху бутон Готово.</p>

<p>Допълнителните ресурси ще бъдат предмет на същите правила като основните ресурси. Например, това означава, че ако се
	опитате да създадете една 2 часова резервация с Ресурс 1, който разполага с максимална дължина от 3 часа и с Ресурс 2,
	който има максимална дължина от 1 час, вашата резервация ще бъде отхвърлена.</p>

<p>Можете да разгледате конфигурационните детайли на ресурс, като позиционирате мишката над името на ресурса.</p>

<h3>Повтарящи се дати</h3>

<p>Резервацията може да бъде конфигурирана да се повтори няколко различни начини. Крайната дата се включва за всички опции за повторение.</p>

<p>Опциите за повторение дават възможност за гъвкави възможности за повторение. Например: Дневно повторение, на всеки 2 дни ще
	създаде резервация през един ден за целия указан период. Седмично повторение, всяка 1 седмица Понеделник, Сряда и
	Петък ще създаде резервация на всеки от тези дни, всяка седмица  за указания период. Ако сте създали резервация
	на 2011-01-15, с месецно повторение, през 3 месеца на същата дата от месеца ще се създаде резервация
	всеки трети месец на 15-ти. Тъй като 2011-01-15 е третата Събота на Януари, същият пример с избран ден от седмицата,
	ще се повтори всеки трети месец на третата Събота от месеца.</p>

<h3>Допълнителни Участници</h3>

<p>Вие моцете да добавите участници или да поканите други, при правене на резервация. Добавянето на някой ще го включи
	в резервацията и няма да изпрати покана.
	Добавеният потребител ще получи имейл. Поканата на потребител ще стартира изпращане на имейл с покана и ще даде възможност на потребителя
	да приеме или откаже поканата. Приемането на поканата добавя потребителя в
	списъка с участниците. Отказът от поканата премахва потребителя от списъка с поканените.
</p>

<p>
	Общият брой на участниците е ограничен от капацитета на ресурса.
</p>

<h3>Аксесоари</h3>

<p>За аксесоарите може да се миски, като за обекти използвани по време на резервация. Например проектори или столове. За да
	добавите аксесоари във вашата резервация, кликнете върху линк Добавяне в дясно на заглавие Аксесоари. От тук ще можете
	да избирате количество на всеки от наличните аксесоари. Достъпните количества зависят от броя на резервираните преди това
	аксесоари.</p>

<h3>Резервация от името на други</h3>

<p>Администраторите на приложението и администраторите на групи могат да правят резервации от името на други потребители, като кликнат
	върху линк промяна, вдясно на потребителското име.</p>

<p>Администраторите на приложението и администраторите на групи могат също да променят и изтриват резервации, притежавани от
	други потребители.</p>

<h2>Обновяване на резервация</h2>

<p>Вие можете да обновите всяка резервация, която сте създали или която е създадена от ваше име.</p>

<h3>Обновяване на конкретни екземпляри от серия</h3>

<p>
	Ако една резервация съдържа повторения, тогава се създава серия. След като направите промени и обновите
	резервацията, ще бъдете попитан/а, върху кои екземпляри от серията искате да се приложат промените. Можете да приложите
	промените върху екземпляра, който разглеждате (само този екземпляр) и нито един друг екземпляр няма да бъде променен.
	Можете да обновите всички екземпляри, като приложите промените към всеки екземпляр от резервацията, който все още не се е случил.
	Можете да приложите промяната само върху бъдещи екземпляри, което ще обнови всички екземпляри на резервация, включително и
	след разглеждания текущ екземпляр.
</p>

<p>Само администратори на приложението могат да обновят минали резервации.</p>

<h2>Изтриване на резервация</h2>

<p>Изтриването на резервация напълно я премахва от разписанието. Тя повече никога няма да бъде видима никъде в
	Booked Scheduler</p>

<h3>Изтриване на конкретен екземпляр от серия</h3>

<p>Подобно на актуализиране на резервация, когато изтривате можете да изберете кои екземпляри искате да изтриете.</p>

<p>Само Администратори на приложението могат да изтриват минали резервации.</p>

<h2>Добавяне на Резервация в Календар (Outlook&reg;, iCal, Mozilla Lightning, Evolution)</h2>

<p>Когато разглеждате или обновявате резервация, ще се показва бутон Добявяне в Outlook. Ако Outlook е инсталиран на вашия компютър,
	вие ще бъдете попитан да добавите срещата. Ако не е инсталиран, ще бъдете подканен/а да изтеглите един
	.ics файл. Това е стандартен формат за календар. Можете да използвате този файл за добавяне на резервацията във всяко приложение,
	което поддържа iCalendar файлов формат.</p>

<h2>Абониране за календари</h2>

<p>Календари могат да бъдат публикувани за разписания, ресурси и потребители. За да работи тази функция, администраторът трябва да има
	конфигуриран абонамент ключ в конфигурационния файл. За да се даде възможност за абонамент за календар на ниво Разписание и
	ресурс, просто включете абонаменти, при управление на разписанието или ресурса. За да включите индивидуален абонамент
	за календар, отворете Разписание -> Моят календар. В дясната част на страницата ще намерите линк за разрешаване или изключване
	на абонаменти за календар.
</p>

<p> За да се абонирате за календар на разписание, отворете Разписание -> Ресурсен Календар и изберете желаното разписание. В дясната
	страна на страницата, ще намерите линк за абонамент за текущия календар. Абонирането за ресурсен календар
	следва същите стъпки. За да се абонирате за вашия персонален календар, отворете разписание -> Моят календар. В дясната
	страна на страницата, ще намерите линк за абонамент за текущия календар.</p>

<h3>Календарен клиент (Outlook&reg;, iCal, Mozilla Lightning, Evolution)</h3>

<p>В повечето случаи, кликайки върху линк Абониране за този календар, явтоматично ще създаде абонамент във вашия
	календарен клиент. За Outlook, ако не се добави автоматично, отворите изгледа на календара, след това клик с десен бутон
	Моите календари и изберете Добавяне на календар -> От интернет. Поставете URL-а изписан под линк Абониране за този календар в
	Booked Scheduler.</p>

<h3>Google&reg; календар</h3>

<p>Отваряне настройките на Google календар. Кликнете върху таб календари. Щракнете върху разглеждане на интересни календари. Кликнете добавяне чрез URL.
	Поставете URL-а изписан под линк Абониране за този календар в Booked Scheduler.</p>

<h2>Квоти</h2>

<p>Администраторите имат възможност за конфигуриране на правила на квоти, базирани на различни критерии. Ако вашата резервация
	би нарушила някоя квота, ще бъдете уведомени и резервацията ще бъде отказана.</p>

</div>

{include file='globalfooter.tpl'}