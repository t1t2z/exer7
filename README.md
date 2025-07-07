# QRT-DETR: Post-Training Quantization for Real-Time Detection Transformer

## Introduction

The Real-Time Detection Transformer (RT-DETR) is a state-of-the-art model for real-time object detection, achieving remarkable performance in complex vision tasks. However, its high computational and memory demands pose significant challenges for deployment on resource-constrained edge devices, such as smartphones and electric vehicles. To address these challenges, we propose **QRT-DETR**, a novel post-training quantization (PTQ) framework tailored for RT-DETR, enabling efficient deployment while maintaining high detection accuracy. QRT-DETR introduces the **EMA-MSE Quantizer** and a **two-stage reconstruction-aware quantization strategy**, which collectively mitigate quantization errors and optimize model performance under low-bit settings (e.g., 8-bit and 4-bit).

### EMA-MSE Quantizer

The EMA-MSE Quantizer integrates Exponential Moving Average (EMA) for dynamic tracking of activation distributions with Mean Squared Error (MSE) as a quality metric for precise quantization range calibration. This approach effectively handles the long-tailed distribution characteristics of RT-DETR's visual data, reducing quantization distortions. The figure below illustrates the activation distributions of the "decoder layers 0" after applying different quantization methods, highlighting the improved performance of the EMA-MSE Quantizer.

![IMG_4689](https://github.com/user-attachments/assets/04554164-90a5-4597-904f-a7f3c8f89a6b)

*Figure 2: Activation distributions of "decoder layers 0's axis value range" after applying (a) MinMax Quantizer, (b) EMA-MSE Quantizer, and (c) after fine-tuning.*

### Two-Stage Reconstruction-Aware Quantization Strategy

To further enhance quantization accuracy, QRT-DETR employs a two-stage reconstruction-aware quantization strategy. This method iteratively optimizes weight and activation quantization parameters, minimizing performance degradation even under extreme 4-bit quantization. The process is visualized in the following figure, showcasing the structured pipeline for quantizing RT-DETR.

![image](https://github.com/user-attachments/assets/cd121d44-ed67-4f21-9155-4b98a21d850c)

*Figure 3: QRT-DETR quantization process, illustrating the stages of model preprocessing, quantizer integration, and parameter calibration.*

### Performance Validation

The effectiveness of QRT-DETR is demonstrated through extensive experiments on the COCO and PASCAL VOC 2012 datasets, achieving 3.3% and 3.9% improvements in Average Precision (AP) under 8-bit and 4-bit quantization, respectively, compared to the baseline RT-DETR. The Grad-CAM visualization below compares the results of uniform quantization and QRT-DETR quantization, highlighting improved feature retention.

<div style="display: flex; justify-content: center; gap: 10px;">
  <img src="https://github.com/user-attachments/assets/51e49481-08d5-4ebb-937e-340925f66f95" alt="图片1" style="max-width: 30%;">
  <img src="https://github.com/user-attachments/assets/51e49481-08d5-4ebb-937e-340925f66f95" alt="图片1" style="max-width: 30%;">
  <img src="https://github.com/user-attachments/assets/51e49481-08d5-4ebb-937e-340925f66f95" alt="图片1" style="max-width: 30%;">
  

</div>
*Figure 4: Grad-CAM Visualization comparing (a) results of uniform quantization and (b) results of QRT-DETR quantization.*

The proposed QRT-DETR framework provides a robust solution for deploying transformer-based real-time object detectors on resource-constrained devices, offering both theoretical insights and practical guidelines for efficient model optimization. The code is available at [https://github.com/sldey/huo/QRT-DETR](https://github.com/sldey/huo/QRT-DETR).
