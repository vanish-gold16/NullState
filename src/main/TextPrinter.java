package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextPrinter {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void waitEnter() {
        System.out.print("...");
        try {
            IN.readLine();
        } catch (IOException ignored) {}
    }


    public void type(String text, int pauseMs) {
        if(text == null){
            return;
        }
        System.out.print(text);
        if(pauseMs > 0){
            try{
                Thread.sleep(pauseMs);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public void skip(){
        System.out.println("\n\n\n\n\n\n\n");
    }

    public void backstreet(){
        System.out.println("(aby pokračovat ve čtení textu stiskněte Enter po ..., jinak ne)");
        waitEnter();

        type("Říká se, že okvětní lístky sakury padají rychlostí pěti centimetrů za sekundu. ", 3000);
        type("Nemohu si to ověřit. ", 2000);
        type("V tomhle městě žádné sakury nerostou. \n", 3000);
        type("""           
                |   | |   | |     |    
                | | | |   | |     |    
                |   | | _ | | _ _ | _ _\n""", 1000);
        waitEnter();
        skip();
        type("Pronásleduje mně krutá bolest hlavy, někde v oblasti přístavu. ", 1000);
        type("Všechno kolem je syrové a nepřátelské. ", 2000);
        type("Asfalt je stále mokrý po nedávném, zde vzácném dešti. \n", 3000);
        type("Úzká ulička, sevřená podivně starými budovami. \n", 2000);
        type("Vzpomínky na včerejšek se pomalu vracejí. ", 2000);
        type("Útržky událostí. \n", 2000);
        type("A pak i důvod, proč ti v chrámu tepá bolest. \n", 2000);
        type("Arasaka.\n", 4000);
    }

    public void market(){
        type("Trh v Malé Číně. Srdce tohohle města.\n", 2000);
        type("Kdysi jsem ho považoval za krásné místo. ", 3000);
        type("Ano… ", 1000);
        type("tehdy, když jsem sem přijel ze svého klidného a útulného Juneau.\n", 3000);
        type("Jednoho z posledních měst v Severní Americe, kde ještě přežívá skutečný les. ", 4000);
        type("Ano.\n", 2000);
        type("Čeká tam na mě ještě někdo?\n", 2000);
        type("Od chvíle, kdy se Aljaška po čtvrté korporátní válce v roce 2084 odtrhla od NUSA, ", 3000);
        type("nemám s rodinou žádný kontakt.\n", 3000);
        type("A vrátit se tam… ", 3000);
        type("to už také není možné.\n", 2000);
        waitEnter();
    }

    public String marketHint(){
        return "\n*Měl bych nejdříve jit za Bestii...*\n";
    }

    public void bar(){
        type("Bez přehánění legendární místo, které stálo už dávno před Datacrashem.\n", 3000);
        type("Všechny vážné žoldácké zakázky se domlouvaly za jeho zdmi.\n", 2000);
        type("Bestie – neotřesitelná královna Afterlife – v poslední době nepouští dovnitř jen tak někoho.", 4000);
        type("Teď je to místo jen pro „své“.\n", 2000);
        type("A já mezi ně patřil.\n", 2000);
        type("Pamatuji si ten bar plný lidí, naplněný hlučnými sny o budoucnosti a tichými výčitkami z minulosti.\n", 4000);
        type("Teď je tu prázdno. ", 2000);
        type("Zima. \n", 2000);
        type("Tohle město nakonec spolkne každého.\n", 2000);
        waitEnter();
    }

    public void clinic(){
        type("Místo, kde mi už nesčetněkrát zachránili život.\n", 3000);
        type("Viktor.", 1000);
        type("Můj anděl strážný.\n", 1000);
        waitEnter();
    }

    public void metroEnter(){
        type("Kdysi místo zábavy pro skejťáky, prostor pro graffiti a jedno z mála skutečně tichých míst ve městě.", 5000);
        type("Alespoň do nedávna.", 2000);
        type("Zhruba před týdnem se tam usadila Arasaka.\n", 3000);
        type("Údajně kvůli „udržení pořádku“ –", 2000);
        type("prý příliš mnoho rabovačů a jiných problémových existencí.\n", 3000);
        type("Jenže celé město už dávno ví, že se tam nejspíš nacházejí tunely spojující Night City s Dogtownem.\n", 5000);
        type("Nezávislým městem a kapsou anarchie v Pacifica.\n", 3000);
        type("Pokud Arasaka ten průchod najde, získá absolutní kontrolu nad všemi podzemními obchody ve městě.\n", 4000);
        type("A já je hodlám předběhnout.\n", 2000);
        waitEnter();
    }

    public void tunels(){
        type("Špinavé. Plné krys, jiskřících kabelů a zápachu, který se nedal ignorovat.", 3000);
        type("Zatracený Vex.\n", 1000);
        type("Kupodivu tu nebyl ani jediný „pracovník“ Arasaky.\n", 3000);
        type("Zdá se, že je nakonec zabila vlastní byrokracie.\n", 3000);
        waitEnter();
    }

    public void dogtown(){
        type("Velkolepý a obávaný Dogtown.\n", 2000);
        type("Útočiště pro ty, kteří se nikdy nevešli do rámce zákona.", 3000);
        type("Hlídky projíždějí ulicemi.", 2000);
        type("Vojáci patrolují na každém rohu.\n", 2000);
        type("Musím být opatrný.\n", 1000);
        waitEnter();
    }

    public void building(){
        type("Starý, rozviklaný dům.\n", 2000);
        type("Podle vzhledu postavený před víc než sto lety.", 3000);
        type("Možná to kdysi býval obyčejný obytný dům.\n", 3000);
        type("Teď z něj zůstala jen opuštěná skořápka bez elektřiny a bez jakéhokoli komfortu, ", 4000);
        type("na který si lidstvo kdysi zvyklo.\n", 2000);
        type("Ideální úkryt pro netrunnera, že?\n", 2000);
        type("Vexi… ", 2000);
        type("prosím.", 1000);
        type("Ať jsi tam.\n", 2000);
        waitEnter();
    }

    public void serverRoom(){
        type("Stojím přede dveřmi.", 2000);
        type("Zatmělo se mi před očima a nohy sotva poslouchají.\n", 3000);
        type("Zpoza dveří se ozývá bzučení běžící techniky a sotva slyšitelný lidský dech.\n", 4000);
        type("Srdce mi buší tak zběsile, jako by se chtělo vyrvat z hrudi.", 3000);
        type("Copak jsem byl vždycky takový zbabělec?", 2000);
        type("A vůbec… co vlastně znamená být zbabělcem?\n", 3000);
        waitEnter();
    }
}
