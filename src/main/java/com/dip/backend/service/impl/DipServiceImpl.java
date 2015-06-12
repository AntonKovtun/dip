package com.dip.backend.service.impl;

import com.dip.backend.service.IDipService;
import com.dip.common.dto.DipDto;
import com.dip.frontend.web.model.DipModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DipServiceImpl implements IDipService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public DipDto mainMethod(DipModel dipModel) throws Exception {
        DipDto dipDto = new DipDto();
        int rowCount = Integer.parseInt(dipModel.getSize()) / Integer.parseInt(dipModel.getLenghHromosom());
        int colCount = Integer.parseInt(dipModel.getLenghHromosom());

        //------generate first population--------------
        dipDto = generateFirstPopulation(dipModel);

        //--------------generate matrix sm-------------------------
        dipDto.setMatrixSm(generateMatrixSm(Integer.parseInt(dipModel.getSize()), dipModel));

        //-----------calculation CF for population---------------------
        calculationCf(dipDto.getMatrixSm(), Integer.parseInt(dipModel.getSize()), dipDto.getMatrix(), rowCount, colCount);

        return dipDto;
    }

    private DipDto generateFirstPopulation(DipModel dipModel) {
        DipDto dipDto = new DipDto();
        //------------generate first population random method (string)-----------------
        List<String> firstPopulation = new ArrayList<>();
        int populationSize = Integer.parseInt(dipModel.getSize());
        int a = 1;

        for (int i = 0; i < populationSize; i++){
            firstPopulation.add(String.valueOf(a));
            a++;
        }
        Collections.shuffle(firstPopulation);


        //-------population (string) to int[][]--------
        int rowCount = Integer.parseInt(dipModel.getSize()) / Integer.parseInt(dipModel.getLenghHromosom());
        int colCount = Integer.parseInt(dipModel.getLenghHromosom());

        int array[][] = new int[rowCount][colCount];
        int r = 0;
        for (int i = 0; i < rowCount; i++) {
            int k = 0;
            for (int j = colCount-1; j >= 0; j--) {
                array[i][k] = Integer.parseInt(firstPopulation.get(r));
                k++;
                r++;
            }
        }

        dipDto.setSize(dipModel.getSize()); //first population size
        dipDto.setPopulation(firstPopulation); //first population string
        dipDto.setMatrix(array); //first population int[][]
        dipDto.setRowCount(String.valueOf(rowCount));
        dipDto.setColCount(String.valueOf(colCount));

        return dipDto;
    }

    private int[][] generateMatrixSm(int populationSize, DipModel dipModel) {

        int matrixSm[][] = new int[populationSize][populationSize];
        int kk = 0;
        for (;;) {
            int i = (int) (Math.random() * populationSize);
            int j = (int) (Math.random() * populationSize);

            if (i == j)
                continue;
            else {
                if (matrixSm[i][j] == 0) {
                    matrixSm[i][j] = 1;
                    matrixSm[j][i] = 1;
                    kk++;
                } else
                    continue;
            }
            if (kk == Integer.parseInt(dipModel.getSdnCount()))
                break;

        }

        return matrixSm;
    }

    private int calculationCf(int[][] matrixSm, int populationSize, int[][] matrixPopulation, int rowCount, int colCount){
        //int arrCoordinat = new int[][];
        List<Integer> coordinatList = new ArrayList<>();
        List<Integer> coordinatMatrixPopList = new ArrayList<>();
        List<Integer> lenghSvyaz = new ArrayList<>();

        for (int i = 0; i <populationSize; i++){
            matrixSm[i][i] =0;
        }
        for (int i = 0; i < populationSize; i++) {
            for (int j = i; j < populationSize; j++) {
                if (matrixSm[i][j] == 1){
                    coordinatList.add(i+1);
                    coordinatList.add(j+1);
                }
            }
        }

        for (int k = 0; k < coordinatList.size(); k++){
            for (int i = 0; i < rowCount; i++){
                for (int j = 0; j < colCount; j++){
                    if (matrixPopulation[i][j] == coordinatList.get(k)){
                        coordinatMatrixPopList.add(i);
                        coordinatMatrixPopList.add(j);
                    }
                }
            }
        }

        for (int i = 0; i < coordinatMatrixPopList.size(); i = i + 4){
            int x = coordinatMatrixPopList.get(i);
            int y = coordinatMatrixPopList.get(i+1);
            int x1 = coordinatMatrixPopList.get(i+2);
            int y1 = coordinatMatrixPopList.get(i+3);

            int xx1 = 0;
            int yy1 = 0;

            if (x1 >= x) {
                xx1 = x1 - x;
            } else {
                xx1 = x - x1;
            }
            if (y1 >= y) {
                yy1 = y1 - y;
            } else {
                yy1 = y - y1;
            }

            int lengSvyazi = xx1 + yy1;

            lenghSvyaz.add(lengSvyazi);
        }


        int criticalLength = 0;
        for (Integer a : lenghSvyaz){
            if (a >= criticalLength){
                criticalLength = a;
            }
        }

        return criticalLength;
    }

}
