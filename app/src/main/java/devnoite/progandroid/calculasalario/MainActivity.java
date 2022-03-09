package devnoite.progandroid.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText edit_sb, edit_dep;
    private RadioGroup group1, group2, group3, group4;
    private RadioButton vtsim, vtnao, vasim, vanao, vrsim, vrnao, standard, basico, superr, master;
    private Button btcalcular, btLimpar;
    private TextView salariobruto;

    double PS;
    double INSS;
    double SB;// = Double.parseDouble(edit_sb.getText().toString());
    int NP;// = Integer.parseInt(edit_dep.getText().toString());
    double VT;
    double VA;
    double VR;
    double BC;
    double IRFF;
    double SL;
    double TP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit_sb = findViewById(R.id.edit_sb);
        edit_dep = (EditText) findViewById(R.id.edit_dep);
        group1 = (RadioGroup) findViewById(R.id.radio_group1);
        group2 = (RadioGroup) findViewById(R.id.radio_group2);
        group3 = (RadioGroup) findViewById(R.id.radio_group3);
        group4 = (RadioGroup) findViewById(R.id.radio_group4);
        btcalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);
        salariobruto = findViewById(R.id.salario_bruto);
        vtsim = findViewById(R.id.vt_sim);
        vtnao = findViewById(R.id.vt_nao);
        vrsim = findViewById(R.id.vr_sim);
        vrnao = findViewById(R.id.vr_nao);
        vanao = findViewById(R.id.va_nao);
        vasim = findViewById(R.id.va_sim);
        standard = findViewById(R.id.standardd);
        basico = findViewById(R.id.basicoo);
        superr = findViewById(R.id.superrr);
        master = findViewById(R.id.masterr);


        btcalcular.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                 SB = Double.parseDouble(edit_sb.getText().toString());
                NP = Integer.parseInt(edit_dep.getText().toString());


                if (edit_sb.getText().toString().isEmpty()) {
                    edit_sb.setError(getString(R.string.valida_sb));
                } else if (edit_dep.getText().toString().isEmpty()) {
                    edit_dep.setError(getString(R.string.valida_dep));
                } else if (group1.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), R.string.valida_vt, Toast.LENGTH_SHORT).show();
                } else if (group2.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), R.string.valida_va, Toast.LENGTH_SHORT).show();
                } else if (group3.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), R.string.valida_vr, Toast.LENGTH_SHORT).show();
                } else if (group4.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), R.string.valida_ps, Toast.LENGTH_SHORT).show();
                } else {




                    /*
                     * NP = número de dependentes TP = tipo de plano de saúde SL = salário líquido
                     * SB = salário bruto INSS = desconto INSS IRFF = desconto de Imposto de renda
                     * retido na fonte PS = desconto plano de saúde VT = vale transporte VR = vale
                     * refeição VA = vale alimentação, BC = base calculo IRFF
                     */

                    if (SB > 0 && SB <= 3000 && standard.isChecked()) {
                        PS = 60;
                    } else if (SB > 0 && SB <= 3000 && basico.isChecked()) {
                        PS = 80;
                    } else if (SB > 0 && SB <= 3000 && superr.isChecked()) {
                        PS = 95;
                    } else if (SB > 0 && SB <= 3000 && master.isChecked()) {
                        PS = 130;
                    } else if (SB > 3000 && standard.isChecked()) {
                        PS = 80;
                    } else if (SB > 3000 && basico.isChecked()) {
                        PS = 110;
                    } else if (SB > 3000 && superr.isChecked()) {
                        PS = 135;
                    } else if (SB > 3000 && master.isChecked()) {
                        PS = 180;
                    }

                    if (SB > 0 && SB <= 1212) {
                        INSS = SB * 0.075;
                    } else if (SB >= 1212.01 && SB <= 2427.35) {
                        INSS = (0.09 * SB - 18.18);
                    } else if (SB >= 2427.80 && SB <= 3641.03) {
                        INSS = (0.12 * SB - 91);
                    } else if (SB >= 3641.70 && SB <= 7087.23) {
                        INSS = (0.14 * SB - 163.82);
                    } else {
                        INSS = 828.39;
                    }


                    if (vtsim.isChecked()) {
                        VT = (SB * 0.06);
                    } else {
                        VT = 0;

                    }

                    if (vasim.isChecked()) {
                        if (SB > 0 && SB <= 3000) {
                            VA = 15;
                        }
                        if (SB >= 3001.01 && SB <= 5000) {
                            VA = 25;
                        }
                        if (SB > 5000) {
                            VA = 35;
                        }
                    } else {
                        VA = 0;

                    }

                    if (vrsim.isChecked()) {
                        if (SB > 0 && SB <= 3000) {
                            VR = 22 * 2.60;
                        }
                        if (SB >= 3000.01 && SB <= 5000) {
                            VR = 22 * 3.65;
                        }
                        if (SB > 5000) {
                            VR = 22 * 6.50;
                        }
                    } else {
                        VR = 0;
                    }


                    BC = SB - INSS - (189.59 * NP);

                    if (BC > 0 && BC <= 1903.98) {
                        IRFF = 0;
                    } else if (BC >= 1903.99 && BC <= 2826.65) {
                        IRFF = (BC * 0.075) - 142.80;
                    } else if (BC >= 2826.66 && BC <= 3751.05) {
                        IRFF = (0.15 * BC - 354.80);
                    } else if (BC >= 3751.06 && BC <= 4664.68) {
                        IRFF = (0.225 * BC - 636.13);
                    } else {
                        IRFF = (BC * 0.275 - 869.36);
                    }

                    SL = SB - INSS - VT - VR - VA - IRFF - PS;

                    salariobruto.setText(String.valueOf("O salário líquido é de: " + SL + "\n" + "O salário bruto é de: " + SB + "\n" + " IRFF: " + IRFF + "\n" + "INSS: " + INSS + "\n" + "VT: " + VT + "\n" + "VR: " + VR + "\n" + "VA: " + VA + "\n" + "Base cáculo: " + BC + "\n" + "Valor plano de saúde: " + PS));
                }
            }

        });

        btLimpar.setOnClickListener(v ->

        {
            edit_sb.setText("");
            edit_dep.setText("");
            group1.setActivated(false);
            group2.setActivated(false);
            group3.setActivated(false);
            group4.setActivated(false);
            edit_sb.requestFocus();
        });

    }
}