# QRT-DETR: Post-Training Quantization for Real-Time Detection Transformer

## Table of Contents
  - [Introduction](#introduction)
    - [EMA-MSE Quantizer](#ema-mse-quantizer)
    - [Two-Stage Reconstruction-Aware Quantization Strategy](#two-stage-reconstruction-aware-quantization-strategy)
    - [Performance Validation](#performance-validation)
  - [Getting Started](#getting-started)
    - [Install](#install)
    - [Data Preparation](#data-preparation)
    - [Run](#run)
  - [Results on COCO and PASCAL VOC](#results-on-coco-and-pascal-voc)
  - [Citation](#citation)
  - [Acknowledgements](#acknowledgements)

## Introduction

The Real-Time Detection Transformer (RT-DETR) is a state-of-the-art model for real-time object detection, achieving remarkable performance in complex vision tasks. However, its high computational and memory demands pose significant challenges for deployment on resource-constrained edge devices, such as smartphones and electric vehicles. To address these challenges, we propose **QRT-DETR**, a novel post-training quantization (PTQ) framework tailored for RT-DETR, enabling efficient deployment while maintaining high detection accuracy. QRT-DETR introduces the **EMA-MSE Quantizer** and a **two-stage reconstruction-aware quantization strategy**, which collectively mitigate quantization errors and optimize model performance under low-bit settings (e.g., 8-bit and 4-bit).

### EMA-MSE Quantizer

The EMA-MSE Quantizer integrates Exponential Moving Average (EMA) for dynamic tracking of activation distributions with Mean Squared Error (MSE) as a quality metric for precise quantization range calibration. This approach effectively handles the long-tailed distribution characteristics of RT-DETR's visual data, reducing quantization distortions. The figure below illustrates the activation distributions of the "decoder layers 0" after applying different quantization methods, highlighting the improved performance of the EMA-MSE Quantizer.

   <div align=center>
     <img src="https://github.com/user-attachments/assets/04554164-90a5-4597-904f-a7f3c8f89a6b">
   </div>


*Figure 2: Activation distributions of "decoder layers 0's axis value range" after applying (a) MinMax Quantizer, (b) EMA-MSE Quantizer, and (c) after fine-tuning.*

### Two-Stage Reconstruction-Aware Quantization Strategy

To further enhance quantization accuracy, QRT-DETR employs a two-stage reconstruction-aware quantization strategy. This method iteratively optimizes weight and activation quantization parameters, minimizing performance degradation even under extreme 4-bit quantization. The process is visualized in the following figure, showcasing the structured pipeline for quantizing RT-DETR.

 <div align=center>
     <img src="https://github.com/user-attachments/assets/cd121d44-ed67-4f21-9155-4b98a21d850c">
   </div>

*Figure 3: QRT-DETR quantization process, illustrating the stages of model preprocessing, quantizer integration, and parameter calibration.*

### Performance Validation

The effectiveness of QRT-DETR is demonstrated through extensive experiments on the COCO and PASCAL VOC 2012 datasets, achieving 3.3% and 3.9% improvements in Average Precision (AP) under 8-bit and 4-bit quantization, respectively, compared to the baseline RT-DETR. The Grad-CAM visualization below compares the results of uniform quantization and QRT-DETR quantization, highlighting improved feature retention.

  <div align=center>
 8-bit &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6-bit &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 4-bit
  </div>

   <div align=center>
     <img src="https://github.com/user-attachments/assets/51e49481-08d5-4ebb-937e-340925f66f95" alt="图片1" style="max-width: 30%;">
     <img src="https://github.com/user-attachments/assets/348939c5-95f6-4f5a-a07b-845bd987e8eb" alt="图片2" style="max-width: 30%;">
     <img src="https://github.com/user-attachments/assets/e5dad9ec-3463-4f28-80b8-684dabd1eba2" alt="图片3" style="max-width: 30%;">=
   </div>
     <div align=center>
    (a) Uniform Quantization Results
  </div>
      <div align=center>
     <img src="https://github.com/user-attachments/assets/6f385917-1d59-4019-a60a-83066b330a44" alt="图片1" style="max-width: 30%;">
     <img src="https://github.com/user-attachments/assets/d4d273b0-739e-43b3-b1e7-b90ded0af25d" alt="图片2" style="max-width: 30%;">
     <img src="https://github.com/user-attachments/assets/ceecfcc0-1b8d-4ff4-9016-e39f7e3cc549" alt="图片3" style="max-width: 30%;">=
   </div>
   <div align=center>
      (b) QRT-DETR Quantization Results
  </div>



*Figure 4: Grad-CAM Visualization comparing (a) results of uniform quantization and (b) results of QRT-DETR quantization.*

The proposed QRT-DETR framework provides a robust solution for deploying transformer-based real-time object detectors on resource-constrained devices, offering both theoretical insights and practical guidelines for efficient model optimization. 
## Getting Started

### Install

- Clone the QRT-DETR repository.

```bash
git clone https://github.com/sldey/huo/QRT-DETR.git
cd QRT-DETR
```

- Create a conda virtual environment and activate it.

```bash
conda create -n qrt-detr python=3.8 -y
conda activate qrt-detr
```

- Install PyTorch and dependencies. For example:

```bash
conda install pytorch=1.12.1 torchvision cudatoolkit=11.3 -c pytorch
```

- Install additional dependencies required for QRT-DETR.

```bash
pip install -r requirements.txt
```

### Data Preparation

Download and prepare the COCO 2017 dataset for evaluation. The dataset should be structured as follows:

```
├── coco
│   ├── train2017
│   ├── val2017
│   ├── annotations
```

For calibration, randomly select 32 images from the `train2017` set as specified in the paper.

Optionally, prepare the PASCAL VOC 2012 dataset for additional evaluation:

```
├── voc
│   ├── VOC2012
│   ├── train
│   ├── val
```

### Run

Example: Evaluate the quantized RT-DETR model with the EMA-MSE Quantizer and two-stage reconstruction strategy.

```bash
python test_quant.py rt_detr_r18 <YOUR_COCO_DATA_DIR> --quant --ema-mse --two-stage
```

- `rt_detr_r18`: Model architecture, which can be replaced with other RT-DETR variants (e.g., `rt_detr_r50`).
- `--quant`: Enable quantization.
- `--ema-mse`: Use the EMA-MSE Quantizer.
- `--two-stage`: Apply the two-stage reconstruction-aware quantization strategy.
- `<YOUR_COCO_DATA_DIR>`: Path to the COCO dataset directory.

To evaluate on PASCAL VOC 2012, specify the dataset path:

```bash
python test_quant.py rt_detr_r18 <YOUR_VOC_DATA_DIR> --quant --ema-mse --two-stage --dataset voc
```

## Results on COCO and PASCAL VOC

The following table summarizes the performance of QRT-DETR compared to other quantized object detection models on the COCO 2017 dataset. Results demonstrate that QRT-DETR maintains high detection accuracy under low-bit quantization settings.

| Models       | Quantization Method | Bits (W/A) | AP   | AP₅₀ | AP₇₅ |
|--------------|---------------------|------------|------|------|------|
| YOLOv5s      | MinMax [32]         | 8/8        | 38.9 | 56.5 | 41.2 |
| YOLOv5s      | Percentile [32]     | 8/8        | 39.0 | 56.4 | 41.3 |
| DETR-R50     | VT-PTQ              | 8/8        | -    | -    | -    |
| RT-DETR (FP) | None                | 32/32      | 46.4 | 63.7 | 50.3 |
| QRT-DETR     | EMA-MSE + Two-Stage | 8/8        | 46.3 | -    | -    |
| QRT-DETR     | EMA-MSE + Two-Stage | 6/6        | 44.0 | -    | -    |
| QRT-DETR     | EMA-MSE + Two-Stage | 4/4        | 22.6 | -    | -    |

QRT-DETR achieves a 3.3% AP improvement under 8-bit quantization and a 3.9% AP improvement under 4-bit quantization compared to baseline methods, significantly outperforming other approaches like Q-YOLO (14.0 AP at 4-bit). On PASCAL VOC 2012, similar trends are observed, though detailed metrics are available in the paper.

## Citation

If you find this work useful in your research, please consider citing our paper:

```BibTeX
@article{huo2025qrtdetr,
  title={QRT-DETR: Post-Training Quantization for Real-Time Detection Transformer},
  author={Huo, Ying and Wu, Tianle and Shen, Yuxuan and Li, Xiaomeng and Tao, Zhao and Yang, Dawei},
  journal={TBD},
  year={2025},
  url={https://github.com/sldey/huo/QRT-DETR}
}
```

## Acknowledgements

This work is supported by the National Natural Science Foundation of China under Grant No. 61902176, the Natural Science Foundation for Youth of Jiangsu Province under Grant No. BK2018106, the Natural Science Foundation of the Jiangsu Higher Education Institution of China under Grant No. 18KJB520019, and the Funding project of "Qing Lan Project" in Jiangsu Province (2023).
