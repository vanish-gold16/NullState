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
        type("Arasaka.\n\n", 4000);
    }

    public void market(){
        type("Trh v Malé Číně. Srdce tohohle města.", 2000);
        type("Kdysi jsem ho považoval za krásné místo.", 3000);
        type("Ano… ", 1000);
        type("tehdy, když jsem sem přijel ze svého klidného a útulného Juneau.", 3000);
        type("Jednoho z posledních měst v Severní Americe, kde ještě přežívá skutečný les.", 4000);
        type("Jednoho z posledních měst v Severní Americe, kde ještě přežívá skutečný les.", 4000);
        type("Ano. ", 2000);
        type("Čeká tam na mě ještě někdo?", 2000);
        type("Od chvíle, kdy se Aljaška po čtvrté korporátní válce v roce 2084 odtrhla od NUSA, ", 3000);
        type("nemám s rodinou žádný kontakt.\n ", 3000);
        type("A vrátit se tam…", 3000);
        type("to už také není možné.", 2000);
        waitEnter();
    }

    public void bar(){
        type("Bez přehánění legendární místo, které stálo už dávno před Datacrashem.", 3000);
        type("Všechny vážné žoldácké zakázky se domlouvaly za jeho zdmi.", 2000);
        type("Bestie – neotřesitelná královna Afterlife – v poslední době nepouští dovnitř jen tak někoho.", 4000);
        type("Teď je to místo jen pro „své“.\n", 2000);
        type("A já mezi ně patřil.\n", 2000);
        type("Pamatuji si ten bar plný lidí, naplněný hlučnými sny o budoucnosti a tichými výčitkami z minulosti.\n", 4000);
        type("Teď je tu prázdno. ", 2000);
        type("Zima. \n", 2000);
        type("Tohle město nakonec spolkne každého. ", 2000);
        waitEnter();
    }

    public void clinic(){
        type("Místo, kde mi už nesčetněkrát zachránili život.\n", 3000);
        type("Viktor.", 1000);
        type("Můj anděl strážný.", 1000);
        waitEnter();
    }

    public void metroEnter(){
        type("Kdysi místo zábavy pro skejťáky, prostor pro graffiti a jedno z mála skutečně tichých míst ve městě.", 5000);
        type("Alespoň do nedávna.", 2000);
        type("Zhruba před týdnem se tam usadila Arasaka.\n", 3000);
        type("Údajně kvůli „udržení pořádku“ –", 2000);
        type("prý příliš mnoho rabovačů a jiných problémových existencí.", 3000);
        type("Jenže celé město už dávno ví, že se tam nejspíš nacházejí tunely spojující Night City s Dogtownem.", 5000);
        type("Nezávislým městem a kapsou anarchie v Pacifica.\n", 3000);
        type("Pokud Arasaka ten průchod najde, získá absolutní kontrolu nad všemi podzemními obchody ve městě.", 4000);
        type("A já je hodlám předběhnout.", 2000);
        waitEnter();
    }
}
