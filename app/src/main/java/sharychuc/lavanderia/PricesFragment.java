package sharychuc.lavanderia;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PricesFragment extends Fragment {
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnSave;
    private View rootView;
    private EditText editTextArticle;
    private EditText editTextPrice;
    private Spinner spinner;

    public PricesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_prices, container, false);
        btnAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        btnSave = (FloatingActionButton) rootView.findViewById(R.id.fabSave);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextArticle = new EditText(rootView.getContext());
                editTextArticle.setHint("Articulo");
                editTextArticle.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

                editTextPrice = new EditText(rootView.getContext());
                editTextPrice.setHint("$00.00");
                editTextPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                spinner= new Spinner(rootView.getContext());

                TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.tablePrice);
                TableRow tableRow = new TableRow(rootView.getContext());
                tableRow.setId(R.id.reservedNameId);
                tableLayout.addView(tableRow);
                tableRow.addView(editTextArticle);
                tableRow.addView(editTextPrice);
            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
